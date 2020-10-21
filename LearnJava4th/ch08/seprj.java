import java.util.*;



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
	}
}
