implementation code in this directory:
Answer for 3b difference for command and query:
Command and query are both types of messages that allow communication between objects in a system
1. A query is a request to another object in which we expect a return value so that we can use it,
and it should not have side effects on the state of object invoked.
2. A command is an instruction that one object send to another for delegating a task, no return value
and it often changes the state of invoked object or another part of the program.
