<template>
    <div class="content">
				<img src="../assets/Foto-S.jpg" style = "width: 99%;">
				<div class="sing" v-for="song in songs" :key="song.id">
					<div class="box">
					<i class="fa fa-play psbtn" aria-hidden="true"
						:data-name="song.dataName"
						:data-name-author="song.author"
						:data-name-song="song.name"></i>
					</div>
					<div class="box singer">
					<span class="nameSong">{{ song.name }}</span>
					</div>
					<div class="box author">
					<span class="nameAuthor">{{ song.singer }}</span>
					</div>
					<div class="box album">
					{{ song.album }}
					</div>
					<div class="box">
					<i @click="toggleLike(song.id)" class="fa fa-heart like" :class="[song.like ? 'likered' : '']" style="opacity: 1; cursor: pointer;" aria-hidden="true" :data-name-song="song.name" ></i>
          
					</div>
          <i class="fa fa-exclamation-triangle icons" @click="toggleModal(song)" style="cursor: pointer;"></i>  
				</div>
			</div>
      <div v-if="isModalVisible" class="modal">
        <input v-model="report.name" placeholder="Name">
        <input v-model="report.singer" placeholder="Singer">
        <input v-model="report.album" placeholder="Album">
        <button @click="sendReport">Пожаловаться</button>
      </div>
      <div v-if="isModalVisible" class="modal-backdrop"></div>
      <div v-if="notification.isVisible" class="notification" :class="notification.type">{{ notification.message }}</div>

			
</template>

<script>
export default {
  data() {
    return {
      responseData: null,
      playing: false,
      audio: null,
      curID: 0,
      songs: [
          {
          "id": 1,
          "album": "ALBUM",
          "name": "Mr.Jacson",
          "singer": "Outkast",
          "like": false
        }
		  ],
      isModalVisible: false, 
        report: { 
            name: '',
            singer: '',
            album: ''
      },
      notification: {
            isVisible: false,
            message: '',
            type: ''
      }
    };
  },
  mounted() {
  this.fetchSongs();
	//this.addLike();
	//this.removeLike();
  },
  methods: {
    async fetchSongs() {
      try {
        const response = await fetch('http://localhost:8090/getLikedMusic');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        this.songs = data;
      } catch (error) {
        console.error("Fetch error: ", error);
      }
    },
    toggleModal(song) {
        this.report.name = song.name;
        this.report.singer = song.singer;
        this.report.album = song.album;
        this.isModalVisible = !this.isModalVisible;
    },
    async sendReport() {
        const response = await fetch('http://localhost:8090/setReport', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(this.report)
        });
        const data = await response.text();
        if (data == "Жалоба успешно отправлена!") {
          this.showNotification(data, 'success');
        } else {
          this.showNotification(data, 'error');
        }
        
        console.log(data); 
        this.isModalVisible = false; 
    },
    showNotification(message, type) {
        this.notification.message = message;
        this.notification.type = type;
        this.notification.isVisible = true;

        setTimeout(() => {
            this.notification.isVisible = false;
        }, 5000); 
    }
  }
}

</script>


<style scoped>
body{
	font-family: 'Annie Use Your Telescope', cursive;
	margin: 0;
	width: 100%;
	height: 100%;
	background-color: White;
	font-family: 'Poppins', sans-serif;
	font-family: 'Roboto', sans-serif;
	
}

.container {
	/*width: 900px;*/
	height: 100hv;
	background-color: White;
	margin-right: 30%;
	margin-top: 0;
	margin-bottom: 0;
	box-sizing: border-box;
}

p {
	font-size: 16px;
}


.logo {
	margin-left: 0px;
	width: 100%;
	background-color: WhiteSmoke;
	font-size: 58px;
	font-weight: normal;
	position: fixed;
	border-bottom: 1px gray solid;
	top: 0;
	margin-top: 0;
	box-shadow: 0 1px 10px DarkGrey;
}



.player {
	position: fixed;
	display: flex;

	bottom: 0;
	background-color: WhiteSmoke;
	width: 100%;
	/* padding-top: 10px; */
	border-top: 1px gray solid;
	margin-top: 0;
	box-shadow: 0 -1px 10px DarkGrey;
	padding: 10px;
}

.back, .psbtn, .next, .like, .playStop{
	border: 1px WhiteSmoke solid;
	padding: 7px;
}

.like {
	color: Silver;
}

.content {
	/*padding-top: 10px;*/
	padding-bottom: 100px;
	display: grid;
	grid-auto-rows: minmax(45px, auto);


}

.curSinger span{
	padding:  auto 0;
}

.curSinger {
	padding: 10px;
	display: inline-block;
	font-size: 22px;
	width: 100%;
}

.sing {
	/*display: flex;*/
	/*height:35px;*/
	display: grid;
	grid-template-columns: 1fr 10fr 10fr 7fr 1fr;
	/*grid-template-rows: minmax(50px, max-content);*/
	/*grid-template-rows: repeat(5, 1fr);*/
	gap: 20px;

	margin: 5px;
	padding: 10px;
	padding-right: 5px;
	background-color: WhiteSmoke;
}

.box {
	display: grid;
	grid-auto-rows: minmax(35px, max-content);

	/* width: 32px; */
	height: 32px;
	display: inline-block;
	padding: auto 0;
	margin: auto 0;
	vertical-align: center;
	margin-right: 5px;
	word-break: break-all;
}

.singer {
	display: inline-block;
	font-size: 22px;
	width: 90%;

	display: grid;
	/*grid-area: 1/2/2/5;*/
}
/*
.singer span{
	padding:  auto 0;
}
*/

.nameSong {
	font-weight: bold;
	margin-left: 10px;

	display: grid;
	grid-template-rows: minmax(50px, max-content);

}

.album {
	display: inline-block;
	font-size: 22px;
	/* width: 35%; */
	margin-left: 35%;
	text-align: center;

	display: grid;
	/*grid-area: 3/2/4/5;*/
}

.author {
	display: inline-block;
	font-size: 22px;
	width: 80%;
	margin-left: auto;
	text-align: right;
	margin-right: 20px;

	display: grid;
	/*grid-area: 2/2/3/5;*/
}

.likered{
	color: Crimson;
}

.fa fa-play psbt{
	display: grid;
	/*grid-area: 1/1/4/2;*/
}

.icons:hover {
  color: gray;
}

.modal {
    position: fixed; 
    top: 50%;
    left: 50%; 
    transform: translate(-50%, -50%); 
    z-index: 1050; 
    width: 40%; 
    background-color: #fff; 
    padding: 20px; 
    border-radius: 8px; 
    box-shadow: 0 4px 8px rgba(0,0,0,0.1); 
    display: flex; 
    flex-direction: column; 
    gap: 10px; 
}


.modal-backdrop {
    position: fixed; 
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.5); 
    z-index: 1040; 
}

.notification {
    position: fixed;
    top: 20px;
    right: 20px;
    background-color: #f0f0f0;
    color: #333;
    padding: 10px 20px;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    z-index: 1060;
    transition: all 0.5s ease;
}

.notification.success {
    background-color: #d4edda;
    color: #155724;
}

.notification.error {
    background-color: #f8d7da;
    color: #721c24;
}



@media screen and (max-width: 960px){
	.sing {
	display: grid;
	grid-template-columns: 1fr 10fr 10fr 7fr 1fr;
	/*grid-template-rows: minmax(50px, max-content);*/
	grid-template-rows: repeat(3, 1fr);
	gap: 20px;

	margin: 5px;
	padding: 10px;
	padding-right: 5px;
	background-color: WhiteSmoke;
	}
	.fa fa-play psbt{
		display: grid;
		grid-area: 2/1/3/2;
		align-items: center;
	}
	.author {
		display: grid;
		grid-area: 2/2/3/5;
		justify-items: center;
	}
	.album {
		display: grid;
		grid-area: 3/2/4/5;
		justify-items: center;
	}
	.singer {
		display: grid;
		grid-area: 1/2/2/5;
		justify-items: center;
	}
	.fa fa-heart like{
		display: grid;
		grid-area: 2/5/3/6;vertical-align: center;
	}
}

</style>