public class Player {
    private String color;
    private char colorCode;

    /**
     * create a new player with a given color
     * @param color player color
     */
    public Player(String color) {
        this.color = color;
        if (color.equals("black"))
            colorCode = 'B';
        else
            colorCode = 'R';
    }

    /**
     * get color of player
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * get color code
     * @return color code
     */
    public char getColorCode() {
        return colorCode;
    }
}
