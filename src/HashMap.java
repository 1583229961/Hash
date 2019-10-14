public class HashMap {}

//HashMap源码解析

public class HashMap<K,V> extends AbstractMap<K,V>//实现了序列化,克隆接口
    implements Map<K,V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;//序列化的序列号

	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //定义默认的HashMap容量
	 static final int MAXIMUM_CAPACITY = 1 << 30;//定义最大HashMap容量
	static final float DEFAULT_LOAD_FACTOR = 0.75f;//定义因子,当需要进行Map扩容时候使用
	static final int TREEIFY_THRESHOLD = 8;//桶节点的链表长度大于8时转化为红黑树
	 static final int UNTREEIFY_THRESHOLD = 6;//桶节点的链表长度小于6时转化为链表
	 static final int MIN_TREEIFY_CAPACITY = 64;//整个Hashmap元素超过64时也会转化为红黑树
	 
	 /****************常用的内部类**************/
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;					//定义一个节点类,与数组组成hash结构
		
        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
	//定义红黑树的结构
	static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;
        TreeNode(int hash, K key, V val, Node<K,V> next) {
            super(hash, key, val, next);
        }

        /**
         * Returns root of tree containing this node.
         */
        final TreeNode<K,V> root() {
            for (TreeNode<K,V> r = this, p;;) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }

 /****************构造函数**************/
 public HashMap() {//使用默认的加载因子0.75
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

public HashMap(int initialCapacity) {//设置初始化容量,默认加载因子,其中也调用了第三个构造函数
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
	
	public HashMap(int initialCapacity, float loadFactor) {//设置初始化容量,加载自定义加载因子
        if (initialCapacity < 0)//判断容量
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))//判断加载因子
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
	
	
	
	

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }