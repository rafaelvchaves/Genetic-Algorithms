public class StringChromosome {

    String code;
    private int cost;


    public StringChromosome(String code) {
        this.code = code;
        cost = 9999;
    }

    public StringChromosome[] mate(StringChromosome other) {
        int mid = other.code().length() / 2;
        String child1 = this.code.substring(0, mid) + other.code.substring(mid, other.code.length());
        String child2 = other.code.substring(0, mid) + this.code.substring(mid, this.code.length());
        StringChromosome[] children = {new StringChromosome(child1), new StringChromosome(child2)};
        return children;
    }

    public void mutate(int chance) {
        if ((int) (Math.random() * 100) < chance) {
            int index = (int)(Math.random()*code.length());
            char[] codeChars = code.toCharArray();
            codeChars[index] = (char) (int) (Math.random() * 255);
            code = String.valueOf(codeChars);
        }
    }

    public String code() {
        return code;
    }

    public void CalcCost(String goal) {
        cost = 0;
        for (int i = 0; i < goal.length(); i++) {
            cost += (int) (Math.pow((int) (goal.charAt(i)) - (int) (code.charAt(i)), 2));
        }
    }

    public int getCost() {
        return cost;
    }

}
