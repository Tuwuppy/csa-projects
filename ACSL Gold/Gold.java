public class Gold {
    double troyValue;
    public Gold(double value) {
        troyValue = value;
    }
    public void getValue(String input) {
        double goldAmt = Double.parseDouble(input.substring(0,input.indexOf(",")));
        //System.out.println(goldAmt);
        input = input.substring(input.indexOf(",")+2);
        //System.out.println(input);
        String unit = input.substring(0,input.indexOf(","));
        //System.out.println(unit);
        input = input.substring(input.indexOf(",")+2);
        //System.out.println(input);
        int carat = Integer.parseInt(input.substring(0,input.indexOf(",")));
        //System.out.println(carat);
        input = input.substring(input.indexOf(",")+2);
        //System.out.println(input);
        double sellPercent = (100-Integer.parseInt(input))*0.01;
        //System.out.println(sellPercent);

        // CHANGES GOLD AMT BASED ON CARATS
        if (carat == 24) {goldAmt*=1;}
        else if (carat == 22) {goldAmt*=0.917;}
        else if (carat == 18) {goldAmt*=0.75;}
        else if (carat == 14) {goldAmt*=0.583;}
        else if (carat == 12) {goldAmt*=0.5;}
        else if (carat == 8) {goldAmt*=0.333;}
        else if (carat == 6) {goldAmt*=0.25;}
        else {goldAmt*=0.04167;}
        //System.out.println(goldAmt);
        //  THERE WAS PROBABLY A BETTER WAY OF DOING THIS

        if (unit.equals("GN")) {goldAmt*=0.0021;}
        else if (unit.equals("GM")) {goldAmt*=0.0321;}
        else if (unit.equals("PN")) {goldAmt*=0.0500;}
        else if (unit.equals("TR")) {goldAmt*=1;}
        else {goldAmt*=0.9115;}
        //System.out.println(goldAmt);
        double goldValue = goldAmt*troyValue*sellPercent;
        goldValue = Math.round(goldValue*100.0) / 100.0;
        System.out.println(goldValue);
    }
}
