sample_hello_java_with_mysql  
======================  
Sample app of java_web using mysql service.  

This works on CloudFoundry.org.  

CloudFoundryServices and ServiceManager are taken from the following sample snipets:
  http://technoroy.blogspot.com/2011/07/vmware-cloud-foundry-developing-cloud.html

### Prerequisites: ###
    Maven2

### How To Use: ###
    mvn package
    cd sample_hello_java_with_mysql/HelloJavaWithMySQL/target/
    vmc push
