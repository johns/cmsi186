import java.lang.Math;

public class MathFunctions {
    
    public static MathOperation sin = (x,args) -> Math.sin(x);

    public static MathOperation log = (x,args) -> Math.log(x);

    public static MathOperation cos = (x,args) -> Math.cos(x);

    public static MathOperation exp = (x,args) -> Math.exp(x);

    public static MathOperation sqrt = (x,args) -> Math.sqrt(x);
    
    public static MathOperation polynomial = (x,args) -> { 
        double result = 0;
        for(int i = 0; i < args.length; i++){
            result += args[i]*(Math.pow(x,i));
        }
        return result;
    };
}