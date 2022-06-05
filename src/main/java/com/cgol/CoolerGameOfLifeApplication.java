package com.cgol;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.ConditionReader;
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
import com.cgol.coolergameoflife.condition.value.Condition;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CoolerGameOfLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoolerGameOfLifeApplication.class, args);
    }

    public ApplicationRunner applicationRunner() {
        return args -> {
            List<ValueBuilderFacade> tokenReaders = List.of(
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
            );

            try {
                ConditionReader conditionReader = new ConditionReader(tokenReaders);
                CellContext context = new CellContext(new Cell[1]);

                Condition condition1 = conditionReader.readCondition("true");
                Condition condition2 = conditionReader.readCondition("10 > 11");
                Condition condition3 = conditionReader.readCondition("random(100) <= 60");
                Condition condition4 = conditionReader.readCondition("sum(12, sum(1, 2, 3), 2, diff(1, 2)) > 10");

                System.out.println(condition1.calculate(context));
                System.out.println();
                System.out.println(condition2.calculate(context));
                System.out.println();
                System.out.println(condition3.calculate(context));
                System.out.println(condition3.calculate(context));
                System.out.println(condition3.calculate(context));
                System.out.println(condition3.calculate(context));
                System.out.println(condition3.calculate(context));
                System.out.println();
                condition4.calculate(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 12 12 + 11 13 + == true AND
        };
    }
}
