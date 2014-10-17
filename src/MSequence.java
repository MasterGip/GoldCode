import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by mg on 10.10.14.
 */
public class MSequence {
    public boolean truth;
    public String beginConfiguration;
    public ArrayList<Integer> programOfMSequence;
    public int sd;

    MSequence(boolean t, String bC, ArrayList<Integer> pOMS, int s){
        this.truth = t;
        this.beginConfiguration = bC;
        this.programOfMSequence = pOMS;
        this.sd = s;

    }

    public String makeSd(String m){
        for(int i = 0; i < sd ; i++){
            m = m.substring(1) + m.charAt(0);
        }
        return m;
    }

    public static boolean sum(boolean a1, boolean a2){
        return (a1 != a2)? true:false;
    }

    public static boolean intToBoolean(char a){
        return (a == '0')?false:true;
    }

    public String generateMSequence(){
        String s = new String(beginConfiguration);
        //System.out.println(beginConfiguration + " " + s);
        HashSet<String> hashSet = new HashSet<String>();

        String m = "";

        do{

            hashSet.add(s);
            m += s.charAt(s.length() - 1) + "";
            boolean help = false;
            //System.out.println(s);
            for(int i:programOfMSequence){
                //System.out.println("--" + i);
                help = sum(help, intToBoolean(s.charAt(i - 1)));
            }
            if(!truth){
                help = !help;

            }
            String sH = (help)?"1":"0";
//            System.out.println("!" + sH + " " + s);
            s = sH + s.substring(0, s.length() - 1);


        }while(!hashSet.contains(s));
//        System.out.println(m);
        if(sd != 0){
            m = makeSd(m);
        }
        return m;
    }

}
