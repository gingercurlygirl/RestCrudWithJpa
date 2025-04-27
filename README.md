# Testing and Spring boot

## How to run?
1. Set environment variables `MYSQL_USER` and `MYSQL_PASSWORD` according to your MySQL.
2. Run `src/Main.java`

## How to test?
1. Create database in MySql `channel`
2. Create database in MySql `my_integration_test_db` for integration testing code
3. Run tests in IntelliJ IDEA by right-clicking on `java` dir in `test` and select `Run Tests in java`


## Application description

An API service has been created that acts as a bulletin board/forum. Users can connect to the channel and send messages.
The REST API works in such a way that the `/channels/` endpoint can retrieve all existing channels (`GET /channels/`),
create a new channel (`POST /channels/`), update a channel (`PUT /channels/{id}`), and delete (`DELETE /channels/{id}`).
You can also create new messages on a channel (`POST /channels/{id}`, and retrieve all messages on a channel (`GET /channels/{id}`).

The `/messages/` endpoint can retrieve all existing messages (`GET /messages/`), update the message content
(`PUT /messages/{id}`), and delete a specific message (`DELETE /messages/{id}`).

Example of how to enter Postman:

channels: 
```
{
  "name": "first channel"
}
```

messages:
```
{
  "message": "first message"
}
```

## Testing

Strategies used for testing are unit, integration, and component tests. Every test contains useful comments.


### Components:
#### controller
- `createChannelByRequestBody` method tested because it's used as main `POST` method for creating new channels.
- `getChannelById` method tested because it's used as main `GET` method for fetching one channel using `id`.

#### model
- `ChannelDTO` class tested in isolation from Spring and database. All `get` and `set` methods tested.

#### repository
- `existsById` method tested because every channel has id, which confirms existence of the channel
- `findByName` method tested because every channel must have valid name

Test is separated it with`application-test.properties`to ensure that the test is isolated from the production data.

#### service
- `addChannel` method tested to assert adding a channel to database
- `getChannelById` method tested to check that created channel's details match the fetched channel
- `deleteChanel` method tested to assert a channel is deleted from database
- `addMessageToChannel` method tested to assert a message is added to database. Tested for valid channel and invalid channel.

