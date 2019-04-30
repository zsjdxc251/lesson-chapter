package com.lesson.concurrent.bit;


import com.example.common.lang.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 十进制转成十六进制：
 * Integer.toHexString(int i)
 * 十进制转成八进制
 * Integer.toOctalString(int i)
 * 十进制转成二进制
 * Integer.toBinaryString(int i)
 * 十六进制转成十进制
 * Integer.valueOf("FFFF",16).toString()
 * 八进制转成十进制
 * Integer.valueOf("876",8).toString()
 * 二进制转十进制
 * Integer.valueOf("0101",2).toString()
 */
public class NormalExample {

    private static final Logger log = LoggerFactory.getLogger(NormalExample.class);


    /**
     *   二进制和十进制之间的转换
     *   因为计算机只认识0和1所以每组成一个32位的0和1的数代表一个认识的字符
     *
     *
     */
    public static void example1() {

        System.out.println(Integer.valueOf("0111",2));


        //System.out.println(Integer.valueOf(String.valueOf(Long.MAX_VALUE),2));   // 2<<2

        System.out.println(8 << 4);   // 8 * 2^4   n<<m = n * 2^m    8 对应二进制 00000000000000000000000000001000 向左偏移 4 位 00000000000000000000000010000000
//
        System.out.println(8 >> 2);   // 8 / 2^4

        System.out.println(StringTools.toBinaryString(8));


        System.out.println(0x0000);

        System.out.println(Integer.toBinaryString(69905));


        System.out.println(Integer.toBinaryString(-42));

        System.out.println(Integer.toBinaryString(3 << 2));

//        System.out.println(0x000000000000000011);
//
//
//        System.out.println(5|6);
//
//
//        System.out.println(5&~6);

        System.out.println(Integer.toBinaryString(-5));

        System.out.println(Integer.toBinaryString(12));


        System.out.println(Integer.valueOf("1000", 2) | Integer.valueOf("0100", 2));
    }


    /**
     * 位运算符号的使用
     */
    public static class Permission {


        // 1 | 0 = 1, 0|1 = 1 , 0|0=0 , 1|1 =1

        // 1 & 0 = 0, 0&1 = 0 , 0&0=0 , 1&1 =1  如果1代表真的话

        // ~1 = 0 , ~0 = 1   去相反的值

        // 1^1 =0 , 0^0=0 ,1^0=1 标识不同的时候才是1


        private static final int SELECT = 1 << 0;  // 0001

        private static final int DELETE = 1 << 1;  // 0010

        private static final int UPDATE = 1 << 2; // 0100

        private static final int INSERT = 1 << 3;  // 1000


        private int per;

        /**
         * 设置或添加权限权限
         * <p>
         * 思路
         * 有1设置为1,之前为1设置的时候为0还是1
         * <p>
         * 需要保留原来权限
         * 原来权限默认是0 添加权限的话,需要把之前为0,1都设置为1，也就是只要有一个为1都得1,使用或语句是可以满足的
         * <p>
         * 例子:
         * 原来是0000 设置一个 0001的使用或语句 0000|0001 如果有一个是就得1得到最后的结果为 00001
         * <p>
         * 原来是0001 设置一个 0010的使用或语句 0001|0010 结果得 0011
         *
         * @param per
         */
        public void setPer(int per) {
            this.per = this.per | per;
        }

        public int getPer() {
            return per;
        }

        /**
         * 删除权限
         * 思路
         * 只要设置的对应是1都得0，如果之前设置的位数本身为0的话依然为0， 0011 - 0010 = 0001 ，0011 - 1010 = 0001
         * 0011     0011
         * 0010     1010
         * <p>
         * 0001     0001
         * <p>
         * 使用单个运算符判断不了，因为这里需要区分原来值和设置值
         * 原来值时0设置值为0 得0，原来值为1设置值为1 得0, 原来值为1设置为0的时候才得1
         * <p>
         * 原来值时0011 设置值时0110的话想得到0001 ，需要把0110取反1001  0011 & 1001 = 0001
         * 设置删除反过来，也就是是否包含不用删除的那些权限1，使用与的话得到的是最后的权限
         * 也就是需要删除的是 0110 不需要删除的就是1001，看看是否有1001权限着最终如果之前的权限是0011的话，想得到0001的话也就是两个为1才是1其他都是0所以选择使用与
         *
         * @param per
         */
        public void delPer(int per) {

            this.per = this.per & ~per;
        }

        /**
         * 判断是否有这个权限
         * 思路
         * 两个数字为1的时候才表示有这个1 所以使用与
         * <p>
         * 例子
         * 1100 怎么判断有 1000
         *
         * @param per
         * @return
         */
        public boolean hasPer(int per) {
            return (this.per & per) == per;
        }

        /**
         * 判断没有这个权限则为真
         * 思路:
         * 两个设置的值都不为1的时候才为真
         * 不包含这个权限才返回真的话
         * 主要着重于设置的1
         * 匹配的时候相反或者是为0的时候
         * 所以使用 与  1100 & 0011 =0匹配出来为0的话代表没有这个权限
         * 恰好和有正好相反，有的话是因为或语句要两个都是1的时候才是1,所有的话是用与的话匹配出来刚好是自己去匹配的数值
         * 如果匹配出来没有权限的话就是0
         * 1100  ~1100 = 0011 判断没有 ~0010 = 1101
         * 1100  0010
         *
         * @param per
         * @return
         */
        public boolean unPer(int per) {

            return (this.per & per) == 0;
        }
    }


    public static void example2() {

//        System.out.println(Integer.valueOf("1000",2)); // 8
//        System.out.println(Integer.valueOf("0100",2)); // 4
//        System.out.println(Integer.valueOf("0010",2)); // 2
//        System.out.println(Integer.valueOf("0001",2)); //1


        //
//        int p = 3;
//        log.info("{}",((p&~4)==4));

        Permission permission = new Permission();
        permission.setPer(Permission.SELECT);

        System.out.println(permission.getPer());
        permission.delPer(Permission.DELETE);

        System.out.println(permission.getPer());

    }

    public static void example3(){
        System.out.println(StringTools.toBinaryString(11));
        System.out.println("1111111111111111111111111111111".length());
        System.out.println(StringTools.toBinaryString(8));
        System.out.println(Integer.toBinaryString(-1>>>1));

        System.out.println(StringTools.toBinaryString(-16>>>1));


        System.out.println(StringTools.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.valueOf(StringTools.lengthString(32,"1"),2));

    }


    public static void main(String[] args) {

        example3();
    }
}
