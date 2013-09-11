/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.paralleluniverse.data.record;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.paralleluniverse.data.record.Field.*;
import java.util.Random;

/**
 *
 * @author pron
 */
//@RunWith(org.mockito.runners.MockitoJUnitRunner.class) //VerboseMockitoJUnitRunner.class)
public class DynamicRecordTest {
    private static final Random rand = new Random();

    public DynamicRecordTest() {
    }

    @Before
    public void setUp() {
    }

    private static class A {
        private boolean a;
        private byte b;
        private short c;
        private int d;
        private long e;
        private float f;
        private double g;
        private char h;
        private String str;
        private boolean[] aa;
        private byte[] ba;
        private short[] ca;
        private int[] da;
        private long[] ea;
        private float[] fa;
        private double[] ga;
        private char[] ha;
        private String[] stra;

        public A() {
            a = rand.nextBoolean();
            b = (byte) rand.nextInt();
            c = (short) rand.nextInt();
            d = rand.nextInt();
            e = rand.nextLong();
            f = rand.nextFloat();
            g = rand.nextDouble();
            h = (char) rand.nextInt();
            str = "foo";

            aa = new boolean[1];
            ba = new byte[2];
            ca = new short[3];
            da = new int[4];
            ea = new long[5];
            fa = new float[6];
            ga = new double[7];
            ha = new char[8];
            stra = new String[]{"hello", "world"};

            for (int i = 0; i < aa.length; i++)
                aa[i] = rand.nextBoolean();
            for (int i = 0; i < ba.length; i++)
                ba[i] = (byte) rand.nextInt();
            for (int i = 0; i < ca.length; i++)
                ca[i] = (short) rand.nextInt();
            for (int i = 0; i < da.length; i++)
                da[i] = rand.nextInt();
            for (int i = 0; i < ea.length; i++)
                ea[i] = rand.nextLong();
            for (int i = 0; i < fa.length; i++)
                fa[i] = rand.nextFloat();
            for (int i = 0; i < aa.length; i++)
                ga[i] = rand.nextDouble();
            for (int i = 0; i < ha.length; i++)
                ha[i] = (char) rand.nextInt();
        }
    }
    private static final DynamicRecordType<A> drt = new DynamicRecordType<>(DynamicRecordType.Mode.UNSAFE);
    private static final BooleanField<A> $a = drt.booleanField("a");
    private static final ByteField<A> $b = drt.byteField("b");
    private static final ShortField<A> $c = drt.shortField("c");
    private static final IntField<A> $d = drt.intField("d");
    private static final LongField<A> $e = drt.longField("e");
    private static final FloatField<A> $f = drt.floatField("f");
    private static final DoubleField<A> $g = drt.doubleField("g");
    private static final CharField<A> $h = drt.charField("h");
    private static final ObjectField<A, String> $str = drt.objectField("str", String.class);
    private static final BooleanArrayField<A> $aa = drt.booleanArrayField("aa", 1);
    private static final ByteArrayField<A> $ba = drt.byteArrayField("ba", 2);
    private static final ShortArrayField<A> $ca = drt.shortArrayField("ca", 3);
    private static final IntArrayField<A> $da = drt.intArrayField("da", 4);
    private static final LongArrayField<A> $ea = drt.longArrayField("ea", 5);
    private static final FloatArrayField<A> $fa = drt.floatArrayField("fa", 6);
    private static final DoubleArrayField<A> $ga = drt.doubleArrayField("ga", 7);
    private static final CharArrayField<A> $ha = drt.charArrayField("ha", 8);
    private static final ObjectArrayField<A, String> $stra = drt.objectArrayField("stra", String.class, 2);

    @After
    public void tearDown() {
    }

    //////////// General //////////////////
    @Test
    public void testSetDirectGetRecord() {
        A a = new A();
        Record<A> r = drt.newInstance(a);

        assertThat(r.get($a), equalTo(a.a));
        assertThat(r.get($b), equalTo(a.b));
        assertThat(r.get($c), equalTo(a.c));
        assertThat(r.get($d), equalTo(a.d));
        assertThat(r.get($e), equalTo(a.e));
        assertThat(r.get($f), equalTo(a.f));
        assertThat(r.get($g), equalTo(a.g));
        assertThat(r.get($h), equalTo(a.h));
        assertThat(r.get($str), equalTo(a.str));

        for (int i = 0; i < $aa.length; i++)
            assertThat(r.get($aa, i), equalTo(a.aa[i]));
        for (int i = 0; i < $ba.length; i++)
            assertThat(r.get($ba, i), equalTo(a.ba[i]));
        for (int i = 0; i < $ca.length; i++)
            assertThat(r.get($ca, i), equalTo(a.ca[i]));
        for (int i = 0; i < $da.length; i++)
            assertThat(r.get($da, i), equalTo(a.da[i]));
        for (int i = 0; i < $ea.length; i++)
            assertThat(r.get($ea, i), equalTo(a.ea[i]));
        for (int i = 0; i < $fa.length; i++)
            assertThat(r.get($fa, i), equalTo(a.fa[i]));
        for (int i = 0; i < $ga.length; i++)
            assertThat(r.get($ga, i), equalTo(a.ga[i]));
        for (int i = 0; i < $ha.length; i++)
            assertThat(r.get($ha, i), equalTo(a.ha[i]));
        for (int i = 0; i < $stra.length; i++)
            assertThat(r.get($stra, i), equalTo(a.stra[i]));

        boolean[] aa = new boolean[1];
        byte[] ba = new byte[2];
        short[] ca = new short[3];
        int[] da = new int[4];
        long[] ea = new long[5];
        float[] fa = new float[6];
        double[] ga = new double[7];
        char[] ha = new char[8];
        String[] stra = new String[2];

        r.get($aa, aa, 0);
        r.get($ba, ba, 0);
        r.get($ca, ca, 0);
        r.get($da, da, 0);
        r.get($ea, ea, 0);
        r.get($fa, fa, 0);
        r.get($ga, ga, 0);
        r.get($ha, ha, 0);
        r.get($stra, stra, 0);

        assertThat(aa, equalTo(a.aa));
        assertThat(ba, equalTo(a.ba));
        assertThat(ca, equalTo(a.ca));
        assertThat(da, equalTo(a.da));
        assertThat(ea, equalTo(a.ea));
        assertThat(fa, equalTo(a.fa));
        assertThat(ga, equalTo(a.ga));
        assertThat(ha, equalTo(a.ha));
        assertThat(stra, equalTo(a.stra));
    }

    @Test
    public void testSetRecordGetDirect() {
        A a = new A();
        Record<A> r = drt.newInstance(a);

        r.set($a, rand.nextBoolean());
        r.set($b, (byte) rand.nextInt());
        r.set($c, (short) rand.nextInt());
        r.set($d, rand.nextInt());
        r.set($e, rand.nextLong());
        r.set($f, rand.nextFloat());
        r.set($g, rand.nextDouble());
        r.set($h, (char) rand.nextInt());
        r.set($str, "bar");

        for (int i = 0; i < $aa.length; i++)
            r.set($aa, i, rand.nextBoolean());
        for (int i = 0; i < $ba.length; i++)
            r.set($ba, i, (byte) rand.nextInt());
        for (int i = 0; i < $ca.length; i++)
            r.set($ca, i, (short) rand.nextInt());
        for (int i = 0; i < $da.length; i++)
            r.set($da, i, rand.nextInt());
        for (int i = 0; i < $ea.length; i++)
            r.set($ea, i, rand.nextLong());
        for (int i = 0; i < $fa.length; i++)
            r.set($fa, i, rand.nextFloat());
        for (int i = 0; i < $ga.length; i++)
            r.set($ga, i, rand.nextDouble());
        for (int i = 0; i < $ha.length; i++)
            r.set($ha, i, (char) rand.nextInt());

        r.set($stra, 0, "goodbye");

        assertThat(r.get($a), equalTo(a.a));
        assertThat(r.get($b), equalTo(a.b));
        assertThat(r.get($c), equalTo(a.c));
        assertThat(r.get($d), equalTo(a.d));
        assertThat(r.get($e), equalTo(a.e));
        assertThat(r.get($f), equalTo(a.f));
        assertThat(r.get($g), equalTo(a.g));
        assertThat(r.get($h), equalTo(a.h));
        assertThat(a.str, equalTo("bar"));

        for (int i = 0; i < $aa.length; i++)
            assertThat(r.get($aa, i), equalTo(a.aa[i]));
        for (int i = 0; i < $ba.length; i++)
            assertThat(r.get($ba, i), equalTo(a.ba[i]));
        for (int i = 0; i < $ca.length; i++)
            assertThat(r.get($ca, i), equalTo(a.ca[i]));
        for (int i = 0; i < $da.length; i++)
            assertThat(r.get($da, i), equalTo(a.da[i]));
        for (int i = 0; i < $ea.length; i++)
            assertThat(r.get($ea, i), equalTo(a.ea[i]));
        for (int i = 0; i < $fa.length; i++)
            assertThat(r.get($fa, i), equalTo(a.fa[i]));
        for (int i = 0; i < $ga.length; i++)
            assertThat(r.get($ga, i), equalTo(a.ga[i]));
        for (int i = 0; i < $ha.length; i++)
            assertThat(r.get($ha, i), equalTo(a.ha[i]));
        for (int i = 0; i < $stra.length; i++)
            assertThat(r.get($stra, i), equalTo(a.stra[i]));

        assertThat(r.get($stra, 0), equalTo("goodbye"));
    }

    @Test
    public void testSetRecordGetDirect2() {
        A a = new A();
        Record<A> r = drt.newInstance(a);

        boolean[] aa = new boolean[1];
        byte[] ba = new byte[2];
        short[] ca = new short[3];
        int[] da = new int[4];
        long[] ea = new long[5];
        float[] fa = new float[6];
        double[] ga = new double[7];
        char[] ha = new char[8];
        String[] stra = new String[]{"foo", "bar"};

        for (int i = 0; i < aa.length; i++)
            aa[i] = rand.nextBoolean();
        for (int i = 0; i < ba.length; i++)
            ba[i] = (byte) rand.nextInt();
        for (int i = 0; i < ca.length; i++)
            ca[i] = (short) rand.nextInt();
        for (int i = 0; i < da.length; i++)
            da[i] = rand.nextInt();
        for (int i = 0; i < ea.length; i++)
            ea[i] = rand.nextLong();
        for (int i = 0; i < fa.length; i++)
            fa[i] = rand.nextFloat();
        for (int i = 0; i < aa.length; i++)
            ga[i] = rand.nextDouble();
        for (int i = 0; i < ha.length; i++)
            ha[i] = (char) rand.nextInt();

        r.set($aa, aa, 0);
        r.set($ba, ba, 0);
        r.set($ca, ca, 0);
        r.set($da, da, 0);
        r.set($ea, ea, 0);
        r.set($fa, fa, 0);
        r.set($ga, ga, 0);
        r.set($ha, ha, 0);
        r.set($stra, stra, 0);

        for (int i = 0; i < $aa.length; i++)
            assertThat(r.get($aa, i), equalTo(a.aa[i]));
        for (int i = 0; i < $ba.length; i++)
            assertThat(r.get($ba, i), equalTo(a.ba[i]));
        for (int i = 0; i < $ca.length; i++)
            assertThat(r.get($ca, i), equalTo(a.ca[i]));
        for (int i = 0; i < $da.length; i++)
            assertThat(r.get($da, i), equalTo(a.da[i]));
        for (int i = 0; i < $ea.length; i++)
            assertThat(r.get($ea, i), equalTo(a.ea[i]));
        for (int i = 0; i < $fa.length; i++)
            assertThat(r.get($fa, i), equalTo(a.fa[i]));
        for (int i = 0; i < $ga.length; i++)
            assertThat(r.get($ga, i), equalTo(a.ga[i]));
        for (int i = 0; i < $ha.length; i++)
            assertThat(r.get($ha, i), equalTo(a.ha[i]));
        for (int i = 0; i < $stra.length; i++)
            assertThat(r.get($stra, i), equalTo(a.stra[i]));

        assertThat(r.get($stra, 0), equalTo("foo"));
        assertThat(r.get($stra, 1), equalTo("bar"));
    }

    @Test
    public void testArrayOutOfBounds() {
        A a = new A();
        Record<A> r = drt.newInstance(a);

        try {
            r.get($aa, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($aa, 1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($aa, -1, false);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($aa, 1, false);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ba, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ba, 2);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ba, -1, (byte)0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ba, 2, (byte)0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ca, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ca, 3);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ca, -1, (short)0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ca, 3, (short)0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($da, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($da, 4);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($da, -1, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($da, 4, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ea, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ea, 5);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ea, -1, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ea, 5, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($fa, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($fa, 6);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($fa, -1, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($fa, 6, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ga, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ga, 7);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ga, -1, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ga, 7, 0);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ha, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($ha, 8);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ha, -1, 'x');
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($ha, 8, 'x');
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($stra, -1);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.get($stra, 2);
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($stra, -1, "x");
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            r.set($stra, 2, "x");
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}