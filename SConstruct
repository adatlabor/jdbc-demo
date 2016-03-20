# Path of JDK's bin directory
# jdk_bin_path = ""
jdk_bin_path = "/usr/java/default/bin/"

# Compile .java files, generate .class files
javac_env = Environment(JAVACFLAGS='-encoding UTF-8', JAVACLASSPATH=['.', 'lib/ojdbc7.jar'])
my_class_files=javac_env.Java('classes', 'src')

# Generate an unsigned .jar file from .class files
jar_env = Environment()
jar_env.Jar(target='unsigned.jar', source=my_class_files+['conf/MANIFEST.MF', 'resources/'] )

# Sign .jar file
# Create builder (jarsigner)
jarsigner_build = Builder(action=jdk_bin_path+'jarsigner -keystore $KEYSTORE '
    '-storepass webstart -tsa $TSA -signedjar $TARGET $SOURCE webstart')
# Set parameters
jarsigner_env = Environment(BUILDERS={'JarSigner': jarsigner_build}, KEYSTORE='conf/webstart.keystore', TSA='http://timestamp.digicert.com/')
# Sign .jar file
jar_file=jarsigner_env.JarSigner(target='MySignedApplication.jar', source='unsigned.jar')
ojdbc_jar_file=jarsigner_env.JarSigner(target='web/ojdbc7.jar', source='lib/ojdbc7.jar')

# Deploy .jar file and JDBC driver into 'web' directory
jardeploy_env = Environment()
jardeploy_env.Install(target='web/', source=jar_file)
