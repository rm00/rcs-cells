import com.rm00.cells.Strength;
import com.rm00.cells.strength.StrengthFactory;
import com.rm00.cells.strength.StrengthType;
import org.junit.Assert;
import org.junit.Test;

public class PowerStrengthTest {
    private static double extendedLog(double base, double arg) {
        return Math.log(arg)/Math.log(base);
    }

    @Test
    public void ExtendedLog_Is_CorrectlyImplemented() {
        Assert.assertEquals(4., PowerStrengthTest.extendedLog(2., 16.), 1.E-10);
        Assert.assertEquals(5., PowerStrengthTest.extendedLog(10., 1.E5), 1.E-10);
    }

    @Test
    public void When_DistanceEqualsLog_P_X_Should_ReturnOneOverX() {
        double power = 1.25;
        Strength f = StrengthFactory.getStrength(StrengthType.Power, power);
        Assert.assertEquals(0.512, f.getStrengthValue(3.), 1.E-10);
        double x = Math.random()*power*3;
        double distance = PowerStrengthTest.extendedLog(power, x);
        Assert.assertEquals(1./x, f.getStrengthValue(distance), 1.E-10);
    }

    @Test
    public void When_DistanceEqualsZero_Should_ReturnOne() {
        Strength f = StrengthFactory.getStrength(StrengthType.Power, 1.25);
        Assert.assertEquals(1., f.getStrengthValue(0.), 1.E-10);
    }
}
