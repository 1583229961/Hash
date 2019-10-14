public class HashMap {}

//HashMapԴ�����

public class HashMap<K,V> extends AbstractMap<K,V>//ʵ�������л�,��¡�ӿ�
    implements Map<K,V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;//���л������к�

	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //����Ĭ�ϵ�HashMap����
	 static final int MAXIMUM_CAPACITY = 1 << 30;//�������HashMap����
	static final float DEFAULT_LOAD_FACTOR = 0.75f;//��������,����Ҫ����Map����ʱ��ʹ��
	static final int TREEIFY_THRESHOLD = 8;//Ͱ�ڵ�������ȴ���8ʱת��Ϊ�����
	 static final int UNTREEIFY_THRESHOLD = 6;//Ͱ�ڵ��������С��6ʱת��Ϊ����
	 static final int MIN_TREEIFY_CAPACITY = 64;//����HashmapԪ�س���64ʱҲ��ת��Ϊ�����
	 
	 /****************���õ��ڲ���**************/
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;					//����һ���ڵ���,���������hash�ṹ
		
        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
	//���������Ľṹ
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

 /****************���캯��**************/
 public HashMap() {//ʹ��Ĭ�ϵļ�������0.75
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

public HashMap(int initialCapacity) {//���ó�ʼ������,Ĭ�ϼ�������,����Ҳ�����˵��������캯��
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
	
	public HashMap(int initialCapacity, float loadFactor) {//���ó�ʼ������,�����Զ����������
        if (initialCapacity < 0)//�ж�����
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))//�жϼ�������
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