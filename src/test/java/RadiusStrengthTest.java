import com.rm00.cells.Strength;
import com.rm00.cells.strength.StrengthFactory;
import com.rm00.cells.strength.StrengthType;
import org.junit.Assert;
import org.junit.Test;

public class RadiusStrengthTest {
    @Test
    public void When_DistanceLessThanRadius_Should_ReturnCorrectValue() {
        double radius = 8.;
        Strength f = StrengthFactory.getStrength(StrengthType.Radius, radius);
        Assert.assertEquals(0.5, f.getStrengthValue(radius/2.),1.E-10);
        Assert.assertEquals(7./8, f.getStrengthValue(1.), 1.E-10);
    }

    @Test
    public void When_DistanceEqualsOrLargerThanRadius_Should_ReturnZero() {
        double radius = 8.;
        Strength f = StrengthFactory.getStrength(StrengthType.Radius, radius);
        Assert.assertEquals(0, f.getStrengthValue(radius), 1.E-10);
        Assert.assertEquals(0, f.getStrengthValue(radius + Math.random()), 1.E-10);
    }

    @Test
    public void When_DistanceEqualsZero_Should_ReturnOne() {
        Strength f = StrengthFactory.getStrength(StrengthType.Radius, Math.random());
        Assert.assertEquals(1., f.getStrengthValue(0.), 1.E-10);
    }
}
