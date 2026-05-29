package gt.edu.umg.app.repository;

public class CustomTreeStrategy  {

    // Estructura propia de nodos sin usar Collections
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    //  Construcción del árbol manual
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }
        return current;
    }

    //  DFS (Búsqueda en Profundidad - InOrder por ejemplo)
    public void dfs() {
        dfsRecursive(root);
        System.out.println();
    }

    private void dfsRecursive(Node node) {
        if (node != null) {
            dfsRecursive(node.left);
            System.out.print(node.value + " ");
            dfsRecursive(node.right);
        }
    }

    // BFS (Búsqueda en Amplitud) - Sin usar colas de Collections (usando un array manual)
    public void bfs() {
        if (root == null) return;
        
        Node[] queue = new Node[1000];
        int head = 0;
        int tail = 0;
        
        queue[tail++] = root;
        
        while (head < tail) {
            Node current = queue[head++];
            System.out.print(current.value + " ");
            
            if (current.left != null) queue[tail++] = current.left;
            if (current.right != null) queue[tail++] = current.right;
        }
        System.out.println();
    }

    // Altura del Árbol
    public int getAbsoluteHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(calculateHeight(node.left), calculateHeight(node.right));
    }

    // Profundidad de un valor específico
    public int getDepth(int value) {
        return calculateDepth(root, value, 0);
    }

    private int calculateDepth(Node node, int value, int currentDepth) {
        if (node == null) return -1;
        if (node.value == value) return currentDepth;
        
        int leftDepth = calculateDepth(node.left, value, currentDepth + 1);
        if (leftDepth != -1) return leftDepth;
        
        return calculateDepth(node.right, value, currentDepth + 1);
    }

    // Ancestros de un nodo específico
    public void printAncestors(int value) {
        findAncestors(root, value);
        System.out.println();
    }

    private boolean findAncestors(Node node, int value) {
        if (node == null) return false;
        if (node.value == value) return true;

        if (findAncestors(node.left, value) || findAncestors(node.right, value)) {
            System.out.print(node.value + " ");
            return true;
        }
        return false;
    }

    // Validación de Ciclos (En árboles binarios con punteros puros, si un hijo apunta a un ancestro)
    public boolean hasCycle() {
        return checkCycle(root, new Node[1000], 0);
    }

    private boolean checkCycle(Node node, Node[] visited, int count) {
        if (node == null) return false;
        
        // Revisar si ya fue visitado manualmente
        for (int i = 0; i < count; i++) {
            if (visited[i] == node) return true;
        }
        
        visited[count++] = node;
        return checkCycle(node.left, visited, count) || checkCycle(node.right, visited, count);
    }
}
