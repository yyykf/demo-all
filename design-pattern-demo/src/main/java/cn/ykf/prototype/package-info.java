/**
 * 主要用于解决对象创建问题，这部分对象的内容本身比较复杂，从数据库或者远程调用的RPC接口中获取相关对象数据的耗时较长，因此采用对象复制的方式节省耗时和减少创建的复杂过程。
 *
 * @author YuKaiFan
 * @date 2022/3/27
 */
package cn.ykf.prototype;