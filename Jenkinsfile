pipeline
{
    agent any
    tools
    {
       
   maven 'Maven'

    }
    
    stages
    {
        stage('checkout'){
            steps{
               checkout scm
                
               
            }
        }
        
        stage('build'){
            steps{
                
                bat "mvn clean install"
                
            }
        }
        
          stage('sonar analysis'){
            steps{
                
               echo "Sonar"
                withSonarQubeEnv("local sonar")
                {
                    bar "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
                }
                
            }
        }
         stage('Uplodaing of artifactory'){
            steps{
                
              
                
            }
        }
        
       	}
        
    
}
