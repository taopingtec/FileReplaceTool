####    说明
本工具是我在android反编译的过程中写的一个文件内容替换工具，也可用来替换其他文件
该工具的使用方法如下：

        java -jar FileReplaceTool.jar replace.cfg ./replaceDir

其中replace.cfg是内容替换的配置文件，新老内容用英文的','隔开，每行是一对替换条件，具体示例见本工程的示例
./replaceDir是要替换内容的目录，该工具会依据replace.cfg中的配置替换该目录下的所有文件中的内容
