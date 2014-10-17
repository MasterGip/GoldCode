/**
 * Created by mg on 10.10.14.
 */
public class GoldCodeGenerator {
    MSequence m1;
    MSequence m2;

    GoldCodeGenerator(MSequence m_1, MSequence m_2){
        this.m1 = m_1;
        this.m2 = m_2;

    }

    public String generateGoldCode(){
        String s1 = m1.generateMSequence();
        String s2 = m2.generateMSequence();
        System.out.println("M1: "+ s1 +  "\nM2: " + s2);
        String code = "";
        for(int i = 0; i <= s1.length() - 1; i++){
            code += (MSequence.sum(MSequence.intToBoolean(s1.charAt(i)), MSequence.intToBoolean(s2.charAt(i))))?"1":"0";
        }
        return code;
    }
}
