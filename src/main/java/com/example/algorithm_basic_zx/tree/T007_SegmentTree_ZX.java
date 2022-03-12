package com.example.algorithm_basic_zx.tree;

public class T007_SegmentTree_ZX {
    //范围更新
    //范围增加
    // 范围查询， 2-50 的累加和是多少
    public static class SegmentTreeZX {
        private int newN;
        private int[] arr;// arr[]为原序列的信息从0开始，但在arr里是从1开始的
        private int[] sum;// sum[]模拟线段树维护区间和
        private int[] lazy;// lazy[]为累加和懒惰标记
        private int[] change; // change[]为更新的值, 把范围内所有数都变成一样
        private boolean[] update; // update[]为更新慵懒标记

        public SegmentTreeZX(int[] origin) {
            newN = origin.length + 1;
            arr = new int[newN];
            for (int i = 1; i < newN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[newN << 2];
            lazy = new int[newN << 2];
            change = new int[newN << 2];
            update = new boolean[newN << 2];

        }

        // 在初始化阶段，先把sum数组，填好
        // 在arr[l~r]范围上，去build，1~N，
        // i : 这个范围在sum中的下标
        //  [3 2 1 2 6] > sum[1] = l(1) ... + r(5), from arr
        public void build(int l, int r, int i) {
            if (l == r) {
                sum[i] = arr[r];
                return;
            }
            int mid = l + ((r - l) >> 1);
            build( l, mid, i << 1 );
            build( mid + 1, r, i << 1 | 1 );
            pushUp( i );
        }

        private void pushUp(int i) {
            // sum[i] = sum[i <<1] + sum[i<< |1]; same as below
            sum[i] = sum[i * 2] + sum[2 * i + 1];
        }

        // L~R, add C 任务！, add C in L-R
        // i，l~r, current at i, it stands for value l-r
        public void add(int taskL, int taskR, int taskVal, int curL, int curR, int curIndex) {
            if (curL >= taskL && curR <= taskR) {
                sum[curIndex] += taskVal * (curR - curL + 1);
                lazy[curIndex] += taskVal;
                return;
            }
            // clear old lazy task
            int mid = curL + ((curR - curL) >> 1);
            pushDown( curIndex, mid - curL + 1, curR - mid );

            //execute current task
            if (taskL <= mid) {
                add( taskL, taskR, taskVal, curL, mid, curIndex << 1 );
            }
            if (taskR > mid) {
                add( taskL, taskR, taskVal, mid + 1, curR, curIndex << 1 | 1 );
            }

            pushUp( curIndex );

        }

        // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
        // 分发策略是什么
        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int i, int lSize, int rSize) {
            if (update[i]) {
                update[i << 1] = true;
                update[i << 1 | 1] = true;

                change[i << 1] = change[i];
                change[i << 1 | 1] = change[i];
                // clear lazy info
                lazy[i << 1] = 0;
                lazy[i << 1 | 1] = 0;

                sum[i << 1] = change[i] * lSize;
                sum[i << 1 | 1] = change[i] * rSize;
                update[i] = false;
            }
            if (lazy[i] != 0) {
                //pushdown to left one level
                lazy[i << 1] += lazy[i];
                sum[i << 1] += lazy[i] * lSize;

                //pushdown to right one level
                lazy[i << 1 | 1] += lazy[i];
                sum[i << 1 | 1] += lazy[i] * rSize;
                // clear lazy info
                lazy[i] = 0;
            }

        }


        // L~R  所有的值变成C
        // l~r  rt
        public void update(int taskL, int taskR, int taskVal, int curL, int curR, int curIndex) {
            if (curL >= taskL && curR <= taskR) {
                change[curIndex] = taskVal;
                update[curIndex] = true;
                sum[curIndex] = taskVal * (curR - curL + 1);
                lazy[curIndex] = 0;
                return;
            }
            // clear old lazy task
            int mid = curL + ((curR - curL) >> 1);
            pushDown( curIndex, mid - curL + 1, curR - mid );

            //execute current task
            if (taskL <= mid) {
                update( taskL, taskR, taskVal, curL, mid, curIndex << 1 );
            }
            if (taskR > mid) {
                update( taskL, taskR, taskVal, mid + 1, curR, curIndex << 1 | 1 );
            }

            pushUp( curIndex );
        }


        // 1~6 累加和是多少？ 1~8 i
        public long query(int taskL, int taskR, int curL, int curR, int curIndex) {
            if (curL >= taskL && curR <= taskR) {
                return sum[curIndex];
            }
            int mid = curL + ((curR - curL) >> 1);
            pushDown( curIndex, mid - curL + 1, curR - mid );

            long ans = 0;
            if (taskL <= mid) {
                ans += query( taskL, taskR, curL, mid, curIndex << 1 );
            }
            if (taskR > mid) {
                ans += query( taskL, taskR, mid + 1, curR, curIndex << 1 | 1 );
            }
            return ans;
        }

    }

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray( len, max );
            SegmentTreeZX seg = new SegmentTreeZX( origin );
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build( S, N, root );
            Right rig = new Right( origin );
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min( num1, num2 );
                int R = Math.max( num1, num2 );
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add( L, R, C, S, N, root );
                    rig.add( L, R, C );
                } else {
                    seg.update( L, R, C, S, N, root );
                    rig.update( L, R, C );
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min( num1, num2 );
                int R = Math.max( num1, num2 );
                long ans1 = seg.query( L, R, S, N, root );
                long ans2 = rig.query( L, R );
//                System.out.println( "My answer is: " + ans1 + "_ correct ans is:" + ans2 );
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTreeZX seg = new SegmentTreeZX( origin );
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build( S, N, root );
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add( L, R, C, S, N, root );
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update( L, R, C, S, N, root );
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query( L, R, S, N, root );
        System.out.println( sum );

        System.out.println( "对数器测试开始..." );
        System.out.println( "测试结果 : " + (test() ? "通过" : "未通过") );

    }
}
