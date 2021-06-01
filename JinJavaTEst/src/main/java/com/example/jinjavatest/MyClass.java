package com.example.jinjavatest;


public class MyClass
{
    //0,1,0,2,1,0,1,3,2,1,2,1};

    //[4,2,0,3,2,5]        10  ,   预期 ： 9
    public static int trap(int[] height)
    {
        int rtn = 0;
        int indexBase = 0;
        //  int indexNow = 0;
        for (int i = 1; i < height.length; i++)
        {
            int base = height[indexBase];
            int value = height[i];
            if (base == 0)
            {
                indexBase = i;
                continue;
            }
            if (value >= base)
            {
                int area = base * (i - indexBase - 1);

                for (int j = indexBase + 1; j < i; j++) // 中间小于的全截取。
                {
                    int valueNew = height[j];
                    area -= valueNew;
                }
                rtn += area;
                indexBase = i;
            } else
            {
                if (i == height.length - 1) // 最后是小的。
                {
                    int[] intss = new int[height.length  - indexBase];
//                    Arrays.copyOf(height,indexBase);
                    for (int j = 0; j < intss.length; j++)
                    {
                        intss[j] = height[height.length - 1 - j];
                    }
                    rtn += trap(intss);

                }
            }


        }
        return rtn;
    }

    public static void main(String[] args)
    {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //height = new int []{4,2,0,3,2,5};
       // height = new int []{4,2,3,};
        int ss = trap(height);
        int sst = ss;
        System.out.println(ss);
//        Object obj = 99.88;
//        double d = (float)548.5656;
//        Float f = ((Double) obj ).floatValue();
//
//        System.out.println("-----------------------进进----------------------");
//        System.out.println(obj.getClass().getName());
//        System.out.println(f.getClass().getName());

    }


    //输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    public class Solution
    {


    }
}