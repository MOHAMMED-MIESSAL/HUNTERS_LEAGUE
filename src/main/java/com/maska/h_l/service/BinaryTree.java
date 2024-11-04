
package com.maska.h_l.service;
import com.maska.h_l.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {
    private TreeNode root;
    private Map<UUID, TreeNode> nodeMap;

    public BinaryTree() {
        this.root = null;
        this.nodeMap = new HashMap<>();
    }

    /**
     * Insérer un nouvel utilisateur dans l'arbre
     */
    public void insert(User user) {
        if (!nodeMap.containsKey(user.getId())) {
            root = insertRec(root, user);
            nodeMap.put(user.getId(), new TreeNode(user));
        }
    }

    private TreeNode insertRec(TreeNode root, User user) {
        if (root == null) {
            root = new TreeNode(user);
            return root;
        }

        if (user.getId().compareTo(root.user.getId()) < 0) {
            root.left = insertRec(root.left, user);
        } else if (user.getId().compareTo(root.user.getId()) > 0) {
            root.right = insertRec(root.right, user);
        }

        return root;
    }

    /**
     * Rechercher un utilisateur par ID
     */
    public User search(UUID id) {
        TreeNode node = nodeMap.get(id);
        return node != null ? node.user : null;
    }

    /**
     * Supprimer un utilisateur par ID
     */
    public void delete(UUID id) {
        if (nodeMap.containsKey(id)) {
            root = deleteRec(root, id);
            nodeMap.remove(id);
        }
    }

    private TreeNode deleteRec(TreeNode root, UUID id) {
        if (root == null) {
            return null;
        }

        if (id.compareTo(root.user.getId()) < 0) {
            root.left = deleteRec(root.left, id);
        } else if (id.compareTo(root.user.getId()) > 0) {
            root.right = deleteRec(root.right, id);
        } else {
            // Cas 1: Nœud feuille
            if (root.left == null && root.right == null) {
                return null;
            }
            // Cas 2: Un seul enfant
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // Cas 3: Deux enfants
            User successorValue = getSuccessor(root.right);
            root.user = successorValue;
            root.right = deleteRec(root.right, successorValue.getId());
        }
        return root;
    }

    private User getSuccessor(TreeNode node) {
        User successor = node.user;
        while (node.left != null) {
            successor = node.left.user;
            node = node.left;
        }
        return successor;
    }


    /**
     *  Recherche par Username ou Email
     */
    public User searchByNameOrEmail(String input) {
        return searchRecByNameOrEmail(root, input);
    }

    private User searchRecByNameOrEmail(TreeNode node, String input) {
        if (node == null) {
            return null;
        }

        // Vérifie si le nom ou l'email correspond
        if (node.user.getUsername().equalsIgnoreCase(input) || node.user.getEmail().equalsIgnoreCase(input)) {
            return node.user;
        }

        // Recherche dans les sous-arbres gauche et droit
        User foundInLeft = searchRecByNameOrEmail(node.left, input);
        return foundInLeft != null ? foundInLeft : searchRecByNameOrEmail(node.right, input);
    }


    /**
     * Parcours inordre de l'arbre
     */
    public List<User> inorderTraversal() {
        List<User> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(TreeNode root, List<User> result) {
        if (root != null) {
            inorderRec(root.left, result);
            result.add(root.user);
            inorderRec(root.right, result);
        }
    }

    /**
     * Parcours préordre de l'arbre
     */
    public List<User> preorderTraversal() {
        List<User> result = new ArrayList<>();
        preorderRec(root, result);
        return result;
    }

    private void preorderRec(TreeNode root, List<User> result) {
        if (root != null) {
            result.add(root.user);
            preorderRec(root.left, result);
            preorderRec(root.right, result);
        }
    }

    /**
     * Parcours postordre de l'arbre
     */
    public List<User> postorderTraversal() {
        List<User> result = new ArrayList<>();
        postorderRec(root, result);
        return result;
    }

    private void postorderRec(TreeNode root, List<User> result) {
        if (root != null) {
            postorderRec(root.left, result);
            postorderRec(root.right, result);
            result.add(root.user);
        }
    }

    /**
     * Obtenir la hauteur de l'arbre
     */
    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
    }

    /**
     * Vérifier si l'arbre est vide
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Obtenir le nombre de nœuds
     */
    public int size() {
        return nodeMap.size();
    }

    /**
     * Vider l'arbre
     */
    public void clear() {
        root = null;
        nodeMap.clear();
    }

    /**
     * Vérifier si un utilisateur existe
     */
    public boolean contains(UUID id) {
        return nodeMap.containsKey(id);
    }

    /**
     * Obtenir tous les utilisateurs
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(nodeMap.values().stream()
                .map(node -> node.user)
                .collect(Collectors.toList()));
    }

    /**
     * Mettre à jour un utilisateur
     */
    public void update(User user) {
        if (nodeMap.containsKey(user.getId())) {
            TreeNode node = nodeMap.get(user.getId());
            node.user = user;
        }
    }

    /**
     * Classe interne pour représenter un nœud
     */
    private static class TreeNode {
        User user;
        TreeNode left;
        TreeNode right;

        public TreeNode(User user) {
            this.user = user;
            this.left = null;
            this.right = null;
        }
    }
}