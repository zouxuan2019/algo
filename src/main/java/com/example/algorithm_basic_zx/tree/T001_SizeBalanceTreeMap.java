package com.example.algorithm_basic_zx.tree;

public class T001_SizeBalanceTreeMap {
    //each child node of is not smaller in size than the child nodes of its sibling.
    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> l;
        public SBTNode<K, V> r;
        public int size;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;

        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> head = cur.l;
            cur.l = head.r;
            head.r = cur;
            head.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return head;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> head = cur.r;
            cur.r = head.l;
            head.l = cur;
            head.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return head;
        }

        // add call maintain, return new head
        // maintain itself will propogate to it's parent
        // each child node of is not smaller in size than the child nodes of its sibling.
        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate( cur );
                cur.r = maintain( cur.r );
                cur = maintain( cur );
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate( cur.l );
                cur = rightRotate( cur );
                cur.l = maintain( cur.l );
                cur.r = maintain( cur.r );
                cur = maintain( cur );
            } else if (rightRightSize > leftSize) {
                cur = leftRotate( cur );
                cur.l = maintain( cur.l );
                cur = maintain( cur );
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate( cur.r );
                cur = leftRotate( cur );
                cur.l = maintain( cur.l );
                cur.r = maintain( cur.r );
                cur = maintain( cur );
            }
            return cur;
        }

        // Return
        private SBTNode<K, V> findByIndex(K key) {
            SBTNode<K, V> pre = root;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo( cur.key ) == 0) {
                    break;
                } else if (key.compareTo( cur.key ) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private SBTNode<K, V> findFloorNode(K key) {
            SBTNode<K, V> ans = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo( cur.key ) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo( cur.key ) < 0) {
                    ans = cur;
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return ans;
        }

        private SBTNode<K, V> findCeilingNode(K key) {
            SBTNode<K, V> ans = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo( cur.key ) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo( cur.key ) < 0) {
                    cur = cur.l;
                } else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;
        }

        //index is key, add value to specific index
        // maintain the tree after adding
        // return new head
        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<>( key, value );
            } else {
                cur.size++;
                if (key.compareTo( cur.key ) < 0) {
                    cur.l = add( cur.l, key, value );
                } else {
                    cur.r = add( cur.r, key, value );
                }
                return maintain( cur );
            }
        }

        //return new head
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo( cur.key ) > 0) {
                cur.r = delete( cur.r, key );
            } else if (key.compareTo( cur.key ) < 0) {
                cur.l = delete( cur.l, key );
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l == null) {
                    cur = cur.r;
                } else if (cur.r == null) {
                    cur = cur.l;
                } else {
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.r;
                    des.size--;
                    while (des.l != null) {
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if (pre != null) {
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                    cur = des;
                }
            }
            return cur;
        }

        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
            int leftSize = (cur.l != null ? cur.l.size : 0);
            if (kth == leftSize + 1) {
                return cur;
            } else if (kth <= leftSize) {
                return getIndex( cur.l, kth );
            } else {
                return getIndex( cur.r, kth - leftSize - 1 );
            }
        }

        public int size() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException( "invalid parameter." );
            }
            SBTNode<K, V> lastNode = findByIndex( key );
            return lastNode != null && key.compareTo( lastNode.key ) == 0;
        }

        // Add new
        // Update existing
        public void put(K key, V value) {
            if (key == null) {
                throw new RuntimeException( "invalid parameter." );
            }
            SBTNode<K, V> lastNode = findByIndex( key );
            if (lastNode != null && key.compareTo( lastNode.key ) == 0) {
                lastNode.value = value;
            } else {
                root = add( root, key, value );
            }
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException( "invalid parameter." );
            }
            if (containsKey( key )) {
                root = delete( root, key );
            }
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException( "invalid parameter." );
            }
            return getIndex( root, index + 1 ).key;
        }

        public V getIndexValue(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException( "invalid parameter." );
            }
            return getIndex( root, index + 1 ).value;
        }

        public V get(K key) {
            if (key == null) {
                throw new RuntimeException( "invalid parameter." );
            }
            SBTNode<K, V> lastNode = findByIndex( key );
            if (lastNode != null && key.compareTo( lastNode.key ) == 0) {
                return lastNode.value;
            } else {
                return null;
            }
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.l != null) {
                cur = cur.l;
            }
            return cur.key;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur.key;
        }

        public K floorKey(K key) {
            if (key == null) {
                throw new RuntimeException( "invalid parameter." );
            }
            SBTNode<K, V> lastNoBigNode = findFloorNode( key );
            return lastNoBigNode == null ? null : lastNoBigNode.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                throw new RuntimeException( "invalid parameter." );
            }
            SBTNode<K, V> lastNoSmallNode = findCeilingNode( key );
            return lastNoSmallNode == null ? null : lastNoSmallNode.key;
        }

        // for test
        public static void printAll(SBTNode<String, Integer> head) {
            System.out.println( "Binary Tree:" );
            printInOrder( head, 0, "H", 17 );
            System.out.println();
        }

        // for test
        public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
            if (head == null) {
                return;
            }
            printInOrder( head.r, height + 1, "v", len );
            String val = to + "(" + head.key + "," + head.value + ")" + to;
            int lenM = val.length();
            int lenL = (len - lenM) / 2;
            int lenR = len - lenM - lenL;
            val = getSpace( lenL ) + val + getSpace( lenR );
            System.out.println( getSpace( height * len ) + val );
            printInOrder( head.l, height + 1, "^", len );
        }

        // for test
        public static String getSpace(int num) {
            String space = " ";
            StringBuffer buf = new StringBuffer( "" );
            for (int i = 0; i < num; i++) {
                buf.append( space );
            }
            return buf.toString();
        }

        public static void main(String[] args) {
            SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
            sbt.put( "d", 4 );
            sbt.put( "c", 3 );
            sbt.put( "a", 1 );
            sbt.put( "b", 2 );
            // sbt.put("e", 5);
            sbt.put( "g", 7 );
            sbt.put( "f", 6 );
            sbt.put( "h", 8 );
            sbt.put( "i", 9 );
            sbt.put( "a", 111 );
            System.out.println( sbt.get( "a" ) );
            sbt.put( "a", 1 );
            System.out.println( sbt.get( "a" ) );
            for (int i = 0; i < sbt.size(); i++) {
                System.out.println( sbt.getIndexKey( i ) + " , " + sbt.getIndexValue( i ) );
            }
            printAll( sbt.root );
            System.out.println( sbt.firstKey() );
            System.out.println( sbt.lastKey() );
            System.out.println( sbt.floorKey( "g" ) );
            System.out.println( sbt.ceilingKey( "g" ) );
            System.out.println( sbt.floorKey( "e" ) );
            System.out.println( sbt.ceilingKey( "e" ) );
            System.out.println( sbt.floorKey( "" ) );
            System.out.println( sbt.ceilingKey( "" ) );
            System.out.println( sbt.floorKey( "j" ) );
            System.out.println( sbt.ceilingKey( "j" ) );
            sbt.remove( "d" );
            printAll( sbt.root );
            sbt.remove( "f" );
            printAll( sbt.root );
        }
    }
}
