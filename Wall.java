public class Wall {
    private String name;
    private Boolean solid;
    private String inspect;

    public Wall(String n, String i, Boolean s){
      name = n;
      solid = s;
      inspect =i;
    }

    public Wall(){
      solid = true;
      name = "wall";
      inspect ="Its a solid wall";
    }

    public void invertSolid(){
      this.solid=!solid;
    }
    public String inspect(){
      return inspect;
    }
    public String getName(){
        return name;
    }
    public void setInspect(String i){
      inspect=i;
    }
    public void setName(String n){
      name=n;
    }
}
