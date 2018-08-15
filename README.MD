Keeps a collection of mappings of which Job to trigger after the completion
of a given job.  If this listener is notified of a job completing that has a
mapping, then it will then attempt to trigger the follow-up job.  This
achieves "job chaining", or a "poor man's workflow".
<p>Generally an instance of this listener would be registered as a global
job listener, rather than being registered directly to a given job.</p>
<p>If for some reason there is a failure creating the trigger for the
follow-up job (which would generally only be caused by a rare serious
failure in the system, or the non-existence of the follow-up job), an error
messsage is logged, but no other action is taken. If you need more rigorous
handling of the error, consider scheduling the triggering of the flow-up
job within your job itself.</p>