public class Wall {
    private Boolean solid;
    private String inspect;
    private int ID;

    public Wall(String i, Boolean s, int id2){
      solid = s;
      inspect = i;
      ID = id2;
    }

    public Wall() {
      solid = true;
      inspect = "Its a solid wall";
    }

    public void invertSolid(){
      this.solid=!solid;
    }
    public String inspect(){
      return inspect;
    }

    public int getID() {
      return ID;
    }
    public void setInspect(String i){
      inspect=i;
    }
}