const http = require('http');
const querystring = require('querystring');

const postData = querystring.stringify({
    'msg' : 'Hello World!'
});

let options = {
    hostname: 'localhost',
    port: 8080,
    path: '/grapRedPacket?redPacketId=1&userId=1',
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Content-Length': Buffer.byteLength(postData)
    }
};
let req;
for (let i = 0; i < 30000; i++) {
    options.path = '/grapRedPacket?redPacketId=1&userId=' + i;
    req = http.request(options, (res) => {
        res.on('data', (chunk) => {
            console.log(`响应主体: ${chunk}`);
        });
    });
    req.on('error', (e) => {
        console.error(`请求遇到问题: ${e.message}`);
    });

    req.write(postData);
}


// req.end();