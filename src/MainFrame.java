import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mg on 10.10.14.
 */
public class MainFrame extends JFrame {

    private final static String DEFAULT_M = "1111111111";
    private final static String DEFAULT_M1_POLYNOM = "x^10 + x^3 + 1";
    private final static String DEFAULT_M2_POLYNOM = "x^10 + x^9 + x^8 + x^6 +x^3 + X^2 + 1";

    public static class View{
        public static JTextField tf_m1Begin;
        public static JTextField tf_polynomM1;
        public static JTextField tf_m2Begin;
        public static JTextField tf_polynomM2;
        public static JLabel lbl_numberOfM1Symbols;
        public static JLabel lbl_numberOfM2Symbols;
        public static JButton btn_show;
        public static JLabel lbl_m1Begin;
        public static JLabel lbl_m2Begin;
        public static JLabel lbl_polynom1;
        public static JLabel lbl_polynom2;
        public static JTextField tf_sd;
        public static JLabel lbl_sd;


    }

//    private int getNumberByIndex(int number, int index){
//        return Integer.parseInt((number + "").charAt(index) + "");
//    }
//
//    private int lengthOfNumber(int num){
//        return (num + "").length();
//    }
//
//    private String getMaxNumberPattern(int max){
//        int lengthOfMax = lengthOfNumber(max);
//        String ret = "";
//        if(lengthOfMax > 1){
//            ret = "([0-9]{1," + (lengthOfMax-1) +"})|(";
//        }
//        //int copyOfMax = max;
//        for(int i = 0 ; i < lengthOfMax; i++){
//            ret += "[0-" + getNumberByIndex(max, i) +"]";
//        }
//        ret += ")|("
//    }

    MainFrame(){
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("GoldCode");
        this.setSize(500, 500);
        //GridLayout gl = new GridLayout();


        View.tf_m1Begin = new JTextField(DEFAULT_M);
        View.lbl_numberOfM1Symbols = new JLabel(View.tf_m1Begin.getText().length() + "");
        View.tf_polynomM1 = new JTextField(DEFAULT_M1_POLYNOM);

        View.tf_m2Begin = new JTextField(DEFAULT_M);
        View.lbl_numberOfM2Symbols = new JLabel(View.tf_m2Begin.getText().length() + "");
        View.tf_polynomM2 = new JTextField(DEFAULT_M2_POLYNOM);
        View.btn_show = new JButton("Generate GoldCode");

        View.lbl_m1Begin = new JLabel("Начальная конфигурация M1:");
        View.lbl_m2Begin = new JLabel("Начальная конфигурация M2:");
        View.lbl_polynom1 = new JLabel("Полином M1:");
        View.lbl_polynom2 = new JLabel("Полином M2:");

        JPanel panel1 = new JPanel(new GridLayout(1, 2));
        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel1.add(View.tf_m1Begin);
        panel1.add(View.lbl_numberOfM1Symbols);
        panel2.add(View.tf_m2Begin);
        panel2.add(View.lbl_numberOfM2Symbols);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        //panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        panel.add(View.lbl_m1Begin);
        panel.add(View.lbl_m2Begin);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(View.lbl_polynom1);
        panel.add(View.lbl_polynom2);
        panel.add(View.tf_polynomM1);
        panel.add(View.tf_polynomM2);

        View.tf_m1Begin.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                View.lbl_numberOfM1Symbols.setText(View.tf_m1Begin.getText().length() +"");
            }
        });

        View.tf_m2Begin.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                View.lbl_numberOfM2Symbols.setText(View.tf_m2Begin.getText().length() +"");
            }
        });

        View.btn_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Pattern mPattern = Pattern.compile("[01]+");
//                Pattern polynomPattern = Pattern.compile("([ ]*(x^([0-9]+))|([01])[ ]*[+])*([ ]*(x^([0-9]+))|([01])[ ]*)");
                String m1Begin = View.tf_m1Begin.getText();
                String m2Begin = View.tf_m2Begin.getText();
                String polynom1 = View.tf_polynomM1.getText();
                String polynom2 = View.tf_polynomM2.getText();
//                if(m1Begin.matches(mPattern.pattern()) && m2Begin.matches(mPattern.pattern())
//                        && polynom1.matches(polynomPattern.pattern()) && polynom2.matches(polynomPattern.pattern())){
                MSequence m1Sequence = null;
                MSequence m2Sequence = null;
                    ArrayList<Integer> m1 = new ArrayList<Integer>();
                    ArrayList<Integer> m2 = new ArrayList<Integer>();
                    boolean b1 = true;
                    boolean b2 = true;
                    //Pattern numPattern = Pattern.compile("[0-9]");
                    //Matcher matcher = xPattern.matcher(polynom1);
                    boolean b = true;
                    int i = 0;
                    boolean wasCap = false;
                    String number = "";
                    boolean begin = false;
                    while (i < polynom1.length() && b){
                        char c = polynom1.charAt(i);
                        if((c <= '9') && (c >= '0') ){
                            begin = true;
                            number += c + "";
                        }else{
                            if(c == '^'){
                                wasCap = true;
                                begin = true;
                            }else{
                                if(begin){
                                    if(wasCap){
                                        if(Integer.parseInt(number) <= m1Begin.length()) {
                                            m1.add(Integer.parseInt(number));
                                            number = "";

                                        }else{
                                           // System.out.println(begin + " " + wasCap + " " + number);
                                            b = false;
                                        }
                                        wasCap = false;


                                    }else{
                                        if(number.equals("1")){
                                            b1 = !b1;
                                        }
                                    }
                                    begin = false;
                                }
                            }
                        }
                        i++;
//                        int h = Integer.parseInt(matcher.group().substring(2, matcher.group().length() - 1));
//                        if(h < Integer.parseInt(View.lbl_numberOfM1Symbols.getText())){
//                            m1.add(h);
//                        }else{
//                            b = false;
//                        }
                    }
                    if(wasCap && b){
                        if(Integer.parseInt(number) <= m1Begin.length()) {
                            m1.add(Integer.parseInt(number));
                            number = "";

                        }else{
                            b = false;
                        }
                        wasCap = false;


                    }else{
                        if(number.equals("1")){
                            b1 = !b1;
                        }
                    }
//                    for(int i2:m1){
//                           System.out.println(i2);
//
//                    }
//                    System.out.println();
                    if(b){
//                        System.out.println("!");
//                        matcher = xPattern.matcher(polynom2);
                        i = 0;
                        wasCap = false;
                        number = "";
                        begin = false;
                        while (i < polynom2.length() && b){
                            char c = polynom2.charAt(i);
                            if((c <= '9') && (c >= '0') ){
                                begin = true;
                                number += c + "";
                            }else{
                                if(c == '^'){
                                    wasCap = true;
                                    begin = true;
                                }else{
                                    if(begin){
                                        if(wasCap){
                                            //System.out.println(i);
                                            if(Integer.parseInt(number) <= m2Begin.length()) {
                                                m2.add(Integer.parseInt(number));
                                                number = "";

                                            }else{
                                                b = false;
                                            }
                                            wasCap = false;


                                        }else{
                                            if(number.equals("1")){
                                                b2 = !b2;
                                            }
                                        }
                                        begin = false;
                                    }
                                }
                            }
                            i++;
//                        int h = Integer.parseInt(matcher.group().substring(2, matcher.group().length() - 1));
//                        if(h < Integer.parseInt(View.lbl_numberOfM1Symbols.getText())){
//                            m1.add(h);
//                        }else{
//                            b = false;
//                        }
                        }
                        if(wasCap && b){
                            if(Integer.parseInt(number) <= m1Begin.length()) {
                                m2.add(Integer.parseInt(number));
                                number = "";

                            }else{
                                b = false;
                            }
                            wasCap = false;


                        }else{
                            if(number.equals("1")){
                                b2 = !b2;
                            }
                        }
                        if (b){
                            m1Sequence = new MSequence(b1, m1Begin, m1, 0);
//                            System.out.println(m1Sequence.generateMSequence());
//                            System.out.println("!!!!");
                            m2Sequence = new MSequence(b2, m2Begin, m2, (View.tf_sd.getText().equals(""))?0:Integer.parseInt(View.tf_sd.getText()));
                            if(m1Sequence.generateMSequence().length() == m2Sequence.generateMSequence().length()){

                            }else{
                                b = false;
                            }

                        }
                    }
                    if(!b){
                        JOptionPane.showMessageDialog(Main.mainFrame, "ERROR");
                    }else{
                        GoldCodeGenerator generator = new GoldCodeGenerator(m1Sequence, m2Sequence);
                        String str = generator.generateGoldCode();
//                        String s = str;
                        String s = str.charAt(0) + "";
                        for(int j = 1; j < str.length(); j++){
                            s += str.charAt(j);
                            if(j % 100 == 0){
                                s += "\n";
                            }
                        }
                        JOptionPane.showMessageDialog(Main.mainFrame, s);
                    }
//                }
            }
        });
        JPanel panelS = new JPanel(new GridLayout(3,1));
        View.tf_sd = new JTextField();
        View.lbl_sd = new JLabel("Сдвиг:");
        panelS.add(View.lbl_sd);

        panelS.add(View.tf_sd);
        panelS.add(View.btn_show);
        this.add(panel, BorderLayout.CENTER);
        this.add(panelS, BorderLayout.SOUTH);
        this.setVisible(true);




    }
}
