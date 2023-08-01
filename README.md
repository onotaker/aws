# aws

How to Setup

- Local
    - start docker-compose.yml
- localstack
  - キューの作成
      - aws sqs create-queue --queue-name test-queue --endpoint-url http://localhost:4566
  - メッセージをキューに送信する
      -  aws sqs send-message --queue-url "http://localhost:4566/000000000000/test-queue" --message-body "hello sqs" --endpoint-url http://localhost:4566
  - キューの確認
      - aws sqs list-queues --endpoint-url http://localhost:4566
  - キュー送信結果の確認
      - aws sqs get-queue-attributes --endpoint-url http://localhost:4566 --queue-url http://localhost:4566/000000000000/test-queue --attribute-names ApproximateNumberOfMessages
  - Cognitoの起動
      - docker run -d -p 5000:5000 --name cognito-idp-local motoserver/moto:latest cognito-idp
  - ユーザープールの作成
      - aws cognito-idp create-user-pool --pool-name MyUserPool --query UserPool.Id --output text --endpoint-url http://localhost:5000
  - ユーザープールの設定
      - application-local.yml user-pool-id
  - アプリクライアントの作成
      - aws cognito-idp create-user-pool-client --client-name MyUserPoolClient --user-pool-id ${ユーザープール} --query UserPoolClient.ClientId --output text --endpoint-url http://localhost:5000
  - ユーザーの作成
      - aws cognito-idp admin-create-user --user-pool-id ${ユーザープール} --username scouter1 --user-attributes Name=email,Value=scouter1.com Name=zoneinfo,Value=8 Name=profile,Value=4 --message-action SUPPRESS --endpoint-url http://localhost:5000
  - ユーザー設定の確認
      - aws cognito-idp list-users --user-pool-id ${ユーザープール} --endpoint-url http://localhost:5000