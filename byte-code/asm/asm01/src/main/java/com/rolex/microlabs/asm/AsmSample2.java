/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author rolex
 * @since 2020
 */
public class AsmSample2 extends ClassLoader {

    private static byte[] generate() {
        ClassWriter classWriter = new ClassWriter(0);
        {
            MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        { // 定义对象头；版本号、修饰符、全类名、签名、父类、实现的接口
            classWriter.visit(Opcodes.V1_8,
                    Opcodes.ACC_PUBLIC,
                    "com/rolex/microlabs/asm/AsmSumOfTwoNumbers",
                    null,
                    "java/lang/Object",
                    null);
            // 添加方法；修饰符、方法名、描述符、签名、异常
            MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                    "sum",
                    "(II)I",
                    null,
                    null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitInsn(Opcodes.IADD);
            // 返回
            methodVisitor.visitInsn(Opcodes.IRETURN);
            // 设置操作数栈的深度和局部变量的大小
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }
        // 类完成
        classWriter.visitEnd();
        // 生成字节数组
        return classWriter.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        // 生成二进制字节码
        byte[] bytes = generate();
        // 输出字节码
        outputClazz(bytes);
        // 加载AsmSumOfTwoNumbers
        AsmSample2 generateSumOfTwoNumbers = new AsmSample2();
        Class<?> clazz = generateSumOfTwoNumbers.defineClass("com.rolex.microlabs.asm.AsmSumOfTwoNumbers", bytes, 0, bytes.length);
        // 反射获取 main 方法
        Method method = clazz.getMethod("sum", int.class, int.class);
        Object obj = method.invoke(clazz.newInstance(), 6, 2);
        System.out.println(obj);
    }

    private static void outputClazz(byte[] bytes) {
        // 输出类字节码
        FileOutputStream out = null;
        try {
            String pathName = AsmSample1.class.getResource("/").getPath() + "AsmSumOfTwoNumbers.class";
            out = new FileOutputStream(new File(pathName));
            System.out.println("ASM类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class AsmSumOfTwoNumbers {
        public AsmSumOfTwoNumbers() {
        }

        public int doSum(int var1, int var2) {
            return var1 + var2;
        }
    }
}
