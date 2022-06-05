package com.cgol.coolergameoflife.condition;

import com.cgol.coolergameoflife.condition.builder.fixed.ConditionBuilder;
import com.cgol.coolergameoflife.condition.builder.fixed.NumberBuilder;
import com.cgol.coolergameoflife.condition.builder.fixed.TextBuilder;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.builder.comparison.EqualBuilder;
import com.cgol.coolergameoflife.condition.builder.comparison.LessBuilder;
import com.cgol.coolergameoflife.condition.builder.comparison.LessOrEqualBuilder;
import com.cgol.coolergameoflife.condition.builder.comparison.MoreBuilder;
import com.cgol.coolergameoflife.condition.builder.comparison.MoreOrEqualBuilder;
import com.cgol.coolergameoflife.condition.builder.comparison.NotEqualsBuilder;
import com.cgol.coolergameoflife.condition.builder.function.CountNeighboursBuilder;
import com.cgol.coolergameoflife.condition.builder.function.DiffBuilder;
import com.cgol.coolergameoflife.condition.builder.function.NotBuilder;
import com.cgol.coolergameoflife.condition.builder.function.RandomBuilder;
import com.cgol.coolergameoflife.condition.builder.function.SumBuilder;
import com.cgol.coolergameoflife.condition.builder.logical.AndBuilder;
import com.cgol.coolergameoflife.condition.builder.logical.OrBuilder;
import com.cgol.coolergameoflife.condition.facade.NamedValueBuilderFacade;
import com.cgol.coolergameoflife.condition.facade.SimpleValueBuilderFacade;
import com.cgol.coolergameoflife.condition.facade.ValueBuilderFacade;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.token.TokenReader;
import com.cgol.coolergameoflife.condition.token.Tokenizer;
import com.cgol.coolergameoflife.condition.value.Condition;
import com.cgol.coolergameoflife.condition.value.Number;
import com.cgol.coolergameoflife.condition.value.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ConditionReader {

    // TODO: move or refactor
    public static final ConditionReader DEFAULT_CONDITION_READER = new ConditionReader(
            List.of(
                    new SimpleValueBuilderFacade(new TokenReader("\"[^\"]*\"", Token.Type.OPERAND_STRING), new TextBuilder()),
                    new SimpleValueBuilderFacade(new TokenReader("[0-9]+", Token.Type.OPERAND_NUMBER), new NumberBuilder()),
                    new SimpleValueBuilderFacade(new TokenReader("(?i)(true|false)", Token.Type.OPERAND_BOOLEAN), new ConditionBuilder()),

                    new NamedValueBuilderFacade("sum", Token.Type.FUNCTION, SumBuilder::new),
                    new NamedValueBuilderFacade("diff", Token.Type.FUNCTION, DiffBuilder::new),
                    new NamedValueBuilderFacade("random", Token.Type.FUNCTION, RandomBuilder::new),
                    new NamedValueBuilderFacade("count", Token.Type.FUNCTION, CountNeighboursBuilder::new),
                    new NamedValueBuilderFacade("not", Token.Type.FUNCTION, NotBuilder::new),

                    new NamedValueBuilderFacade("AND", Token.Type.OPERATOR_LOW, AndBuilder::new),
                    new NamedValueBuilderFacade("OR", Token.Type.OPERATOR_LOW, OrBuilder::new),

                    new SimpleValueBuilderFacade(new TokenReader(",", Token.Type.FUNCTION_PARAM_SPLIT), ValueBuilder.EMPTY_BUILDER),
                    new SimpleValueBuilderFacade(new TokenReader("\\(", Token.Type.OPEN_FUNCTION), ValueBuilder.EMPTY_BUILDER),
                    new SimpleValueBuilderFacade(new TokenReader("\\)", Token.Type.CLOSE_FUNCTION), ValueBuilder.EMPTY_BUILDER),

                    new NamedValueBuilderFacade("==", Token.Type.OPERATOR_HIGH, EqualBuilder::new),
                    new NamedValueBuilderFacade("<>", Token.Type.OPERATOR_HIGH, NotEqualsBuilder::new),
                    new NamedValueBuilderFacade(">=", Token.Type.OPERATOR_HIGH, MoreOrEqualBuilder::new),
                    new NamedValueBuilderFacade("<=", Token.Type.OPERATOR_HIGH, LessOrEqualBuilder::new),
                    new NamedValueBuilderFacade(">", Token.Type.OPERATOR_HIGH, MoreBuilder::new),
                    new NamedValueBuilderFacade("<", Token.Type.OPERATOR_HIGH, LessBuilder::new)
            )
    );

    private final Tokenizer tokenizer;
    private final Iterable<ValueBuilder<?>> valueBuilders;

    public ConditionReader(Collection<ValueBuilderFacade> facades) {
        List<TokenReader> readers = new ArrayList<>();
        List<ValueBuilder<?>> builders = new ArrayList<>();

        facades.forEach(facade -> {
            readers.add(facade.getTokenReader());
            builders.add(facade.getValueBuilder());
        });

        this.tokenizer = new Tokenizer(readers);
        this.valueBuilders = builders;
    }

    public Condition readCondition(String input) {
        List<Token> tokens = tokenizer.readTokens(input);
        ProcessedValues values = new ProcessedValues();

        outer:
        for (Token token : tokens) {
            if(token.getType() == Token.Type.FUNCTION_START) {
                values.startOfFunction();
                continue;
            }

            for (ValueBuilder<?> valueBuilder : valueBuilders) {
                if(valueBuilder.canBuild(token)) {
                    values.add(valueBuilder.buildValue(token, values));
                    continue outer;
                }
            }

            throw new IllegalStateException("No builder for token: " + token);
        }

        return values.getCondition();
    }

    public static class ProcessedValues {
        private final List<ValueOrFunctionStart> values = new ArrayList<>();

        public void startOfFunction() {
            values.add(new ValueOrFunctionStart(null, true));
        }

        public void add(Value<?> value) {
            this.values.add(new ValueOrFunctionStart(value, false));
        }

        public List<Value<?>> getAndRemoveFunctionParams() {
            return this.getAndRemoveFunctionParams(-1);
        }

        public List<Value<?>> getAndRemoveFunctionParams(int expectedNumberOfParameters) {
            int current = values.size() - 1;
            while (!values.get(current).isFunctionStart()) {
                current--;
            }

            List<Value<?>> params = values.subList(current + 1, values.size()).stream().map(ValueOrFunctionStart::getValue).collect(Collectors.toList());
            for(int i = 0; i <= params.size(); i++) {
                values.remove(values.size() - 1);
            }

            if(expectedNumberOfParameters != -1 && expectedNumberOfParameters != params.size())
                throw new IllegalStateException("Expected number of parameters for function is " + expectedNumberOfParameters + ", actual number: " + params.size());

            return params;
        }

        public List<Number> getAndRemoveLastTwoNumbers() {
            return getAndRemoveLastTwoValues().stream().map(value -> (Number) value).collect(Collectors.toList());
        }

        public List<Condition> getAndRemoveLastTwoConditions() {
            return getAndRemoveLastTwoValues().stream().map(value -> (Condition) value).collect(Collectors.toList());
        }

        private List<Value<?>> getAndRemoveLastTwoValues() {
            List<Value<?>> lastTwoValues = values.subList(values.size() - 2, values.size()).stream().map(ValueOrFunctionStart::getValue).collect(Collectors.toList());
            if(lastTwoValues.get(0) == null || lastTwoValues.get(1) == null)
                throw new NullPointerException("getAndCastLastTwoValues returned null value");

            values.remove(values.size() - 1);
            values.remove(values.size() - 1);

            return lastTwoValues;
        }

        public Condition getCondition() {
            if(values.size() != 1)
                throw new IllegalStateException("There is more or less than 1 processed values left");

            if(values.get(0).isFunctionStart())
                throw new IllegalStateException("Last processed value is function start");

            if(!(values.get(0).getValue() instanceof Condition))
                throw new IllegalStateException("Last processed value is not condition");

            return (Condition) values.get(0).getValue();
        }

        @AllArgsConstructor
        @Getter
        private static final class ValueOrFunctionStart {
            private final Value<?> value;
            private final boolean isFunctionStart;
        }
    }
}
