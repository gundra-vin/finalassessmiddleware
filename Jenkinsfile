node { 
    stage ('checkout' )
     git 'http://172.18.2.18/gundravin/ProjectManager.git'
     
   stage('Build') {
      // Run the maven build
         bat(/"C:\java cognizant\apache-maven-3.5.0-bin\apache-maven-3.5.0\bin\mvn" clean package/)
         }   
      stage('Testing') {
         bat(/"C:\java cognizant\apache-maven-3.5.0-bin\apache-maven-3.5.0\bin\mvn" clean verify -Dsurefire.skip=true/)      
   }   
}
