# ConfigurationLib
Containing code from murAPI but just singled out for configuration purposes.
## Maven
```HTML
<!--Repositories-->
<repository>
    <id>config-repo</id>
    <url>https://commonly.github.io/repo</url>
</repository>
<!--Dependencies-->
<dependency>
    <groupId>me.jdog.configurationlib</groupId>
    <artifactId>configLib</artifactId>
    <version>0.0.1</version>
    <scope>provided</scope>
</dependency>
```
## Examples
```Java
Config config = new Config(this, "config.yml"); // assuming the class we're in is the main one we're using 'this'
config.create(); // create the config
// config.addDefault("path", "to"); // manually set defaults or..
config.options().copyDefaults(true);
config.loadFromJar(); // make the file in your project and it'll load it.
config.save(); // save the config
```
