package name.lemerdy.eric;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Investment_PlanTest {

    @Test
    public void should_run_single() {
        String output = new InvestmentPlan().compute("0 100 1 2 3 4 5 6 7 8 9 1 1 2");
        System.out.println(output);
        assertThat(output).isEqualTo("Case #1: 1 9 800");
    }

    @Test
    public void should_run_decrease() {
        String output = new InvestmentPlan().compute("0 100 9 9 9 9 8 7 6 5 4 3 2 1");
        System.out.println(output);
        assertThat(output).isEqualTo("Case #1: IMPOSSIBLE");
    }

    @Test
    public void should_run_multiple_plan() {
        String output = new InvestmentPlan().compute("0 100 1 2 3 4 5 6 7 8 9 1 1 2 100 1 2 3 4 5 6 7 8 9 1 1 2");
        System.out.println(output);
        assertThat(output).isEqualTo("Case #1: 1 9 800\nCase #2: 1 9 800");
    }

    @Test
    public void should_run_with_some_other_value() {
        String output = new InvestmentPlan().compute("0 10 1 1 1 1 1 1 1 1 1 1 1 1");
        System.out.println(output);
        assertThat(output).isEqualTo("Case #1: IMPOSSIBLE");
    }

}