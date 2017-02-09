Twitterアカウントを利用したWebサービス開発
==================================
# Twitterを利用することでできること
* __Read Only__ TLやプロフィールなどのデータ取得ができる読み取り権限
* __Read and Write__ 読み取り権限に加え、ツイートの投稿など、DMに関すること_以外_の操作ができる書き込み権限
* __Write and Access direct messages__ 読み取り権限、書き込み権限に加え、DMに関する機能が利用可能

## Twitter利用例
* ツイート分析
  - 行動・趣味趣向分析
  - 口癖分析
  - 性格分析
* フォロー・フォロワー分析
  - コミュニティ・交友分析

## Twitter Rest API -2015/08-
### statuses
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | statuses/user_timeline | ユーザのツイートを取得する |
| GET          | statuses/home_timeline | ホーム画面のTLを取得する |
| GET          | statuses/mentions_timeline | ユーザ宛のメンションを取得する |
| GET          | statuses/retweets_of_me | RTされたツイートを取得する |
| GET          | statuses/show/:id | IDを１つ指定して、ツイートを取得する |
| GET          | statuses/lookup | 複数のIDを指定して、ツイートを取得する |
| GET          | statuses/retweets/:id | 指定ツイートの、RTツイート一覧をオブジェクトで取得する |
| GET          | statuses/retweeters/ids | 指定ツイートをRTしたユーザ一覧を、IDで取得する |
| GET          | statuses/oembed | ツイートIDを指定して、埋め込み用HTMLを取得する |
| POST         | statuses/destroy/:id | ツイートを削除または、RTを取り消す |
| POST         | statuses/update | ツイートを投稿する |
| POST         | statuses/retweet/:id | ツイートIDを指定して、そのIDをRTする |
| POST         | statuses/unretweet/:id | RTを取り消す |
| POST         | statuses/update_with_media | 画像付きのツイートを投稿 |

### direct_messages
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | direct_messages/sent | 送信したDMを取得する |
| GET          | direct_messages/show | 個別のDMを取得する |
| GET          | direct_messages | 受信したDMを取得する |
| POST         | direct_messages/new | DMを送信する |
| POST         | direct_messages/destroy | DMを削除する |

### search
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | search/tweets | ツイートを検索する |

### friendships
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | friendships/show | 指定ユーザ同士のフォロー関係を取得する |
| GET          | friendships/lookup | 自分と指定ユーザのフォロー関係を取得する |
| GET          | friendships/incoming | 自分にフォローリクエストしているユーザID一覧を取得する |
| GET          | friendships/outgoing | 自分が送ったフォローリクエストで、保留中のユーザID一覧を取得する |
| GET          | friendships/no_retweets/ids | RT非表示設定のユーザID一覧を取得する |
| POST         | friendships/create | 指定したユーザをフォローする |
| POST         | friendships/update | 指定ユーザのデバイス通知、リツイート通知の有効/無効を変更 |
| POST         | friendships/destroy | 指定ユーザのリフォロー |

### friends
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | friends/ids | 指定ユーザがフォローしているユーザの一覧を、ユーザIDで取得する |
| GET          | friends/list | 指定ユーザがフォローしているユーザの一覧を、ユーザオブジェクトで取得する |

### followers
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | followers/ids | 指定ユーザのフォロワーの一覧を、ユーザIDで取得する |
| GET          | followers/list | 指定ユーザのフォロワーの一覧を、ユーザオブジェクトで取得する |

### account
| メソッド        | 項目          | 説明          |
|:------------:|:------------:|:------------|
| GET          | account/settings | アカウントの設定情報を取得する |
| GET          | account/verify_credentials | アカウントが有効か否かを確認する |
| POST         | account/settings | アカウントの設定情報を変更する |
| POST         | account/update_profile | プロフィールの設定を変更する |
| POST         | account/remove_profile_banner | バナー画像を削除する |
| POST         | account/update_profile_banner | バナー画像を変更する |
| ~~POST~~     | ~~account/update_delivery_device~~ | ~~更新通知の設定を変更する~~ |
| ~~POST~~     | ~~account/update_profile_image~~ | ~~アイコン画像を変更する~~ |
| ~~POST~~     | ~~account/update_profile_background_image~~ | ~~背景画像を変更する~~ |
