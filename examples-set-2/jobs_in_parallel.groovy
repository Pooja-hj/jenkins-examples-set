//This code snippet will run the same job multiple times in parallel
//a usecase of that is, for example, a system test or load test that requires several workers with heavy i/o or compute.
//it allows you to run each worker on a different machine to distribute the i/o or compute

// in this array we'll place the jobs that we wish to run
def branches = [:]

//running the job 4 times concurrently
//the dummy parameter is for preventing mutation of the parameter before the execution of the closure.
//we have to assign it outside the closure or it will run the job multiple times with the same parameter "4"
//and jenkins will unite them into a single run of the job

for (int i = 0; i < 4; i++) {
  def index = i //if we tried to use i below, it would equal 4 in each job execution.
  branches["branch${i}"] = {
//Parameters:
//param1 : an example string parameter for the triggered job.
//dummy: a parameter used to prevent triggering the job with the same parameters value.
//       this parameter has to accept a different value each time the job is triggered.
    build job: '12-Add-parameters-to-Job', parameters: [
      string(name: 'NAME', value:'test_param')
    ]
  }
}
parallel branches
