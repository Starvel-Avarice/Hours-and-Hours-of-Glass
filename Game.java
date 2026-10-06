public class Game {
    public static void main(String[] args) {
        Wall testWall = new Wall();
        Wall testDoor = new Wall("A locked door with a green gem embeded in it", true);
        Wall[] Walls =new Wall[] {testWall,testWall,testDoor,testWall};

        Item Torch = new Item("torch", "Lights the way", "torchUse");
        Item[] lights= {Torch,Torch};
        Boolean[] tmpWall = {true,true,false,false};
        Tile test = new Tile(lights, Walls, 0);
        Tile test2 = new Tile(tmpWall);
        String[] looked= test.look();
       System.out.println(test.inspectWall(1));
        for(String s:looked){
        System.out.println(s);}

    }
}
