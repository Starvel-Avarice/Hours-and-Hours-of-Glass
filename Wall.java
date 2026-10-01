public class Wall {
    private static String name;
    private static Boolean solid;
    private static String inspect;

    public Wall(String n, String i, Boolean s){
      name = n;
      solid = s;
      inspect =i;
    }
    public Wall(){
      solid = true;
      name = "wall";
      inpsect ="Its a solid wall"
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
