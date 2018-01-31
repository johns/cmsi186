import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MapColorer {

   public void colorMap() {
        int currentRegion = 0;
        int colorIndex = 0;
        boolean redo = false;
        boolean isImpossible = false;
        Region [] regions = Region.getAllRegionsAsArray();

        while (currentRegion < regions.length && currentRegion >= 0) {
            if (redo) {
                colorIndex = regions[currentRegion].getColor() + 1;
                redo = false;
            }
            
            regions[currentRegion].setColor(colorIndex);

            if (!regions[currentRegion].canColorWith(colorIndex)) {
                currentRegion--;
                redo = true;
            }

            if (colorIndex == 4) {
                currentRegion--;
                redo = true;
            }

            currentRegion++;
            colorIndex = 0;
        }

        for (int i = 0; i < regions.length; i++) {
            System.out.println(regions[i].getName() + ": " + regions[i].getColor()); 
            if (regions[i].getColor() > 3) {
                isImpossible = true;
            }  
        }

        if (isImpossible) {
            System.out.println("IMPOSSIBLE MAP");
        }
        else {
            String[] regionList = new String[regions.length * regions.length];
            boolean isUnique = true;
            int arrayIndex = 0;
            for (Region r : Region.getAllRegionsAsArray()) {
                for (Region n : r.getNeighbors()) {
                    isUnique = true;
                    regionList[arrayIndex] = r.getName() + "," + n.getName();
                    for (int i = 0; i < arrayIndex + 1; i++) {             
                        if (regionList[i].equals(n.getName() + "," + r.getName())) {
                            isUnique = false;
                        }
                    }
                    arrayIndex++;
                    if (isUnique) {
                        System.out.println(r.getName() + "," + n.getName());
                    }
                }
            }
        }
        
   }

   public void readMapFromStandardInput() {
       new BufferedReader(new InputStreamReader(System.in))
           .lines()
           .forEach(line -> {
               String[] pair = line.trim().split(",");
               Region region0 = Region.forName(pair[0]);
               Region region1 = Region.forName(pair[1]);
               region0.addNeighbor(region1);
           });
   }

   public static void main(String[] args) {
       MapColorer mapColorer = new MapColorer();
       mapColorer.readMapFromStandardInput();
       mapColorer.colorMap();
   }
}

/**
* This class is full of a lot of stuff you don't need to know.
*
* But don't hesitate to ask about such things if you like.
*/
class Region {

   private static Map<String, Region> allRegions = new TreeMap<>();

   public static Region forName(String name) {
       allRegions.putIfAbsent(name, new Region(name));
       return allRegions.get(name);
   }

   public static Region[] getAllRegionsAsArray() {
       return allRegions.values().toArray(new Region[allRegions.size()]);
   }

   private String name;
   private Integer color;
   private Set<Region> neighbors = new HashSet<>();

   private Region(String name) {
       this.name = name;
   }

   public String getName() {
       return name;
   }

   public Integer getColor() {
       return color;
   }

   public boolean canColorWith(int color) {
       return !neighbors.stream().anyMatch(n -> Objects.equals(n.color,color));
   }

   public void setColor(int color) {
       this.color = color;
   }

   public void removeColor() {
       this.color = null;
   }

   public Set<Region> getNeighbors() {
       return neighbors;
   }

   public void addNeighbor(Region region) {
       Objects.requireNonNull(region);
       neighbors.add(region);
       region.neighbors.add(this);
   }
}