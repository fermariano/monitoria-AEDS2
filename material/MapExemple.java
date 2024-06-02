/**
 * leetcode 652: Find Duplicate Subtrees
 * 
 * @author Thomas Neuenschwander
 * @since 02/06/2024
 * 
 *        [GitHub](https://github.com/thomneuenschwander)
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapExemple {

    public static void main(String[] args) {
        mapProcedure(new HashMap<>());
    }

    static void mapProcedure(Map<String, Integer> m) {
        m.put("Carlos", 24);
        m.put("Jarol", 56);
        m.put("Zenildo", 8);

        mapTraversal(m);
    
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            System.out.println("mudando o valor da chave="+entry.getKey());
            entry.setValue(entry.getValue() + 5);
        }
        System.out.println();
        mapTraversal(m);

        System.out.println("Carlos: " + m.getOrDefault("Carlos", 0)); 
        System.out.println("NonExistentKey: " + m.getOrDefault("NonExistentKey", 0)); 

        
        System.out.println("Contains 'Carlos'? " + m.containsKey("Carlos")); 
        System.out.println("Contains value 56? " + m.containsValue(56)); 

        m.remove("Zenildo");
        System.out.println("After removing 'Zenildo': " + m);


        m.put("Carlos", 30);
        System.out.println("After replacing 'Carlos' value: " + m);

        // Adicionando um novo par chave-valor se a chave não estiver presente
        m.putIfAbsent("NewKey", 100);
        System.out.println("After putIfAbsent: " + m);

        System.out.println();

        for (String key : m.keySet()) 
            System.out.println("Key: " + key);
        System.out.println();

        for (Integer value : m.values()) 
            System.out.println("Value: " + value);
        System.out.println();

        List<String> selectedKeys = new LinkedList<>();
        m.forEach((k, v) -> {
            if(v > 60)
                selectedKeys.add(k);
        });
        selectedKeys.forEach(System.out::println);

        m.clear();
        System.out.println("\nAfter clearing: " + m.isEmpty()); 

    }

    static void mapTraversal(Map<String, Integer> m) {
        Set<Map.Entry<String, Integer>> entrySet = m.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) 
            System.out.println("Key=" + entry.getKey() + " Value=" + entry.getValue());
        System.out.println();
    }
} 
    

