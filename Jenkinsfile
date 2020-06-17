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
                echo "Checkout my code"
                
               
            }
        }
        
        stage('build'){
            steps{
                echo "build"
                bat "mvn clean install"
                
            }
        }
        
       	}
        
    
}
