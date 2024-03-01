# Spring Batch

### Table
| Table                        | Description                                                                                                                                                                                                                                                                                     |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| BATCH_JOB_INSTANCE           | It serves as the top of the overall hierarchy.                                                                                                                                                                                                                                                  |
| BATCH_JOB_EXECUTION_PARAMS   | It contains 0 or more key/value pairs passed to a Job and serves as a record of the parameters with which a job was run. For each parameter that contributes to the generation of a job’s identity, the IDENTIFYING flag is set to true. Note that the table has been denormalized.             |
| BATCH_JOB_EXECUTION          | Every time a Job is run, there is always a new called JobExecution and a new row in this table.                                                                                                                                                                                                 |
| BATCH_STEP_EXECUTION         | This table is similar in many ways to the BATCH_JOB_EXECUTION table, and there is always at least one entry per Step for each JobExecution created.                                                                                                                                             |
| BATCH_JOB_EXECUTION_CONTEXT  | There is exactly one Job ExecutionContext for each JobExecution, and it contains all of the job-level data that is needed for a particular job execution. This data typically represents the state that must be retrieved after a failure, so that a JobInstance can “start where it left off”. |
| BATCH_STEP_EXECUTION_CONTEXT | There is exactly one ExecutionContext per StepExecution, and it contains all of the data that needs to be persisted for a particular step execution. This data typically represents the state that must be retrieved after a failure so that a JobInstance can “start where it left off”.       |
#### Identity
<table>
   <tr>
      <th>Sequence</th>
      <th>Description</th>
   </tr>
   <tr>
      <td>BATCH_JOB_SEQ</td>
      <td rowspan="3">BATCH_JOB_INSTANCE, BATCH_JOB_EXECUTION, and BATCH_STEP_EXECUTION each contain columns ending in _ID. These fields act as primary keys for their respective tables</td>
   </tr>
   <tr>
      <td>BATCH_JOB_EXECUTION_SEQ</td>
   </tr>
   <tr>
      <td>BATCH_STEP_EXECUTION_SEQ</td>
   </tr>
</table>

### Execute batch job with commandline
1. FrogApplication is main class and set property `spring.batch.job.enabled=true`.
    1. `java -jar xxx.jar` starts all job on startup.
    2. `java -jar xxx.jar --spring.batch.job.names=aJob,bJob` narrow down to a specific job or jobs.
2. FrogCustomApplication is main class and set property `spring.batch.job.enabled=false`.
    1. `java -jar xxx.jar aJob,bJob` narrow down to a specific job or jobs.

### Exit code
1. [A Guide to System.exit()](https://www.baeldung.com/java-system-exit)
2. [Spring Boot Exit Codes](https://www.baeldung.com/spring-boot-exit-codes)

### Reference
1. [Spring Batch](https://docs.spring.io/spring-batch/reference/index.html)
2. [Meta-Data Schema](https://docs.spring.io/spring-batch/reference/schema-appendix.html)
3. [Spring Batch 5.0 Migration Guide](https://github.com/spring-projects/spring-batch/wiki/Spring-Batch-5.0-Migration-Guide)
4. [How to Trigger and Stop a Scheduled Spring Batch Job](https://www.baeldung.com/spring-batch-start-stop-job)
5. [study spring batch](https://github.com/a18792721831/studybatch)
6. [*Customizing the Result of JPA Queries with Aggregation Functions*](https://www.baeldung.com/jpa-queries-custom-result-with-aggregation-functions)
