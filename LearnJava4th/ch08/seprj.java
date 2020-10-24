import java.util.*;


interface A 
{
	static int FOO=1; 
	String getFoo();
}
interface B 
{ 
	static int FOO=2;
	Date getDoo();
}

class Cast2<T extends A & B>//<T extends A >//<T extends A & B>
{
	public void foo( T t )
	{
		//System.out.println( t.FOO );
		System.out.println( A.FOO );
		System.out.println( B.FOO );
	}

}

class Variant 
{
	//Object get() { return null; }
	Date get() 
	{ 
		Date d=new Date();
		return d; 
	}
}

class Base { }

class Sub1 extends Base implements Runnable { public void run() { } }
class Sub2 extends Base implements Runnable { public void run() { } }


class Variant2 extends Variant
{
	//Date get() { return (Date)super.get(); }
	Date get() { return super.get(); }
}


class Trap< T > {
    T trapped;

    public void snare( T trapped ) {
        this.trapped = trapped; 
    }

    public T release() {
        return trapped;
    }
}

class Mouse { }

class Bear { }

public class seprj
{
	enum Weekday 
	{

		Sunday(8), Monday(0), Tuesday(1), Wednesday(2), Thursday(4), 
		  Friday(6), Saturday(10) { };

		enum Sub { A, B }
		
		int fun;

		Weekday( int fun ) { this.fun = fun; }

		public int getFun() { return fun; }

	}

	static <T extends Base> T infer( T t1, T t2 ) { return null; }

	static <T> Trap<T> create() 
	{
		return new Trap<T>();
	}

	static <T> Set<T> listToSet( List<T> list )
	{
		Set<T> set = new HashSet<T>();
		set.addAll( list );
		return set;
	}

	public static void main(String args[]) 
	{

		List<?> list = new ArrayList<Date>();
		Set<?> set = listToSet( list );
		
		Trap<Mouse> mouseTrap = new Trap<Mouse>();
        mouseTrap.snare( new Mouse() );
        Mouse mouse = mouseTrap.release();
		Trap<Mouse> mouseTrap2 = create();
		Trap<Bear> bearTrap = create();
		Base base = infer( new Sub1(), new Sub2() );
		// Note: Eclipse 3.1 says this is an error, but it's not
		Runnable runnable = infer( new Sub1(), new Sub2() );
		Date d = new Variant2().get();
		System.out.printf("d: (%s) \n", d.toString());
		//
		Weekday mon=Weekday.Monday;
		Weekday tue = Weekday.Tuesday;
		Weekday wed = Weekday.Wednesday;
		Weekday thu = Weekday.Thursday;
		Weekday fri = Weekday.Friday;
		Weekday sat = Weekday.Saturday;
		Weekday sun = Weekday.Sunday;
		System.out.printf("Weekday: (%d) \n", mon.getFun());
		System.out.printf("Weekday: (%d) \n", tue.getFun());
		System.out.printf("Weekday: (%d) \n", wed.getFun());
		System.out.printf("Weekday: (%d) \n", thu.getFun());
		System.out.printf("Weekday: (%d) \n", fri.getFun());
		System.out.printf("Weekday: (%d) \n", sat.getFun());
		System.out.printf("Weekday: (%d) \n", sun.getFun());
		Cast2Int c1=new Cast2();

		// 
	}
}
