```Java
Config config = new Config(this, "config.yml"); // assuming the class we're in is the main one we're using 'this'
config.create(); // create the config
// config.addDefault("path", "to"); // manually set defaults or..
config.options().copyDefaults(true);
config.loadFromJar(); // make the file in your project and it'll load it.
config.save();
```
