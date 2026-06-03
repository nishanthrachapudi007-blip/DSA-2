class Node {
    int key, height, size;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
        size = 1;
    }
}

public class QuizClashAVL {

    Node root;

    int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    int size(Node N) {
        return (N == null) ? 0 : N.size;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        y.size = size(y.left) + size(y.right) + 1;
        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        x.size = size(x.left) + size(x.right) + 1;
        y.size = size(y.left) + size(y.right) + 1;

        return y;
    }

    int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        node.size = size(node.left) + size(node.right) + 1;

        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void preorder(Node node) {
        if (node != null) {
            System.out.println(
                "Score: " + node.key +
                " Size: " + node.size
            );

            preorder(node.left);
            preorder(node.right);
        }
    }

    public static void main(String[] args) {

        QuizClashAVL tree = new QuizClashAVL();

        int scores[] = {
            820, 540, 910, 770, 880,
            460, 990, 600, 730, 950, 510
        };

        for (int score : scores) {
            tree.root = tree.insert(tree.root, score);
        }

        System.out.println("AVL Tree with Size Fields:\n");

        tree.preorder(tree.root);

        // Score updates
        tree.root = tree.insert(tree.root, 815);
        tree.root = tree.insert(tree.root, 685);

        System.out.println("\nAfter Score Updates:\n");

        tree.preorder(tree.root);
    }
}
