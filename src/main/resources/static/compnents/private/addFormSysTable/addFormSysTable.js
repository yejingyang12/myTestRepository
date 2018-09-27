/**
 * Created by timha on 2018/6/1.
 */
(function () {
    Vue.component('addFormSysTable',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/addFormSysTable/addFormSysTable.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                    addNum:function (index) {
                        var n = this.formData.systemKeyProducts[index].nUseProbability;
                        if( n == 100){
                          this.formData.systemKeyProducts[index].nUseProbability = 100;
                        }else if(n>0||n==0){
                          this.formData.systemKeyProducts[index].nUseProbability++;
                        }else{
                          this.formData.systemKeyProducts[index].nUseProbability=0;
                        }
                    },
                    oddNum:function (index) {
                        var n = this.formData.systemKeyProducts[index].nUseProbability;
                        if(n == 0){
                          this.formData.systemKeyProducts[index].nUseProbability = 0;
                        }else if(n>0){
                          this.formData.systemKeyProducts[index].nUseProbability--;
                        }else{
                          this.formData.systemKeyProducts[index].nUseProbability = 0;
                        }
                    },
                    upNumber:function(index){
                    	this.formData.systemKeyProducts[index].allHave = 2;
                    	this.formData.systemKeyProducts[index].number++;
                    	this.formData.systemKeyProducts[index].productsNumber = this.formData.systemKeyProducts[index].number;
                    },
                    downNumber:function(index){
                    	var num = this.formData.systemKeyProducts[index].number;
                    	if(num>0){
                    		this.formData.systemKeyProducts[index].number--;
                    	}else{
                    		this.formData.systemKeyProducts[index].number = this.formData.systemKeyProducts[index].number
                    	}
                    	this.formData.systemKeyProducts[index].productsNumber = this.formData.systemKeyProducts[index].number;
                    },
                    
                    //获取产品类型信息
                    getProductTypeMethod: function(_self) {
                      ajaxMethod(_self, 'post',
                          'systemCode/querySystemCodeForKeySystemCode', true,
                          '{"codeType":"12"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getProductTypeSuccessMethod);
                    },
                    // 获取产品类型成功
                    getProductTypeSuccessMethod : function(_self, responseData) {
                      for(var i=0;i<responseData.data.length;i++){
                        if(_self.formData.systemKeyProducts.length>=i+1){
                          _self.formData.systemKeyProducts[i].fkExaminStatus = responseData.data[i].systemCode;
                          //进入页面要一致
                          _self.beginContent.systemKeyProducts[i].fkExaminStatus = responseData.data[i].systemCode;
                        }else{
                          _self.formData.systemKeyProducts.push({
                            "fkNationalIsProducts":"",
                            "fkExaminStatus":responseData.data[i].systemCode,
                            "productsNumber":"",
                            "nUseProbability":0,
                            "otherName":""
                          });
                        }
                      }
                      _self.sysProductType = responseData.data;
                    },
                    
                    ls:function(e,index){
                    	e === this.formData.systemKeyProducts[index].allHave ? this.formData.systemKeyProducts[index].allHave = '' : this.formData.systemKeyProducts[index].allHave = e;
                    	this.radioChange(index,this.formData.systemKeyProducts[index].allHave);
                    	//e === this.radio2 ? this.radio2 = '' : this.radio2 = e
                    },
                    radioChange:function(index,allHave){
                    	if(allHave == 1){
                    		this.formData.systemKeyProducts[index].allHave = 1;
                    		this.formData.systemKeyProducts[index].number = 0;
                    		this.formData.systemKeyProducts[index].productsNumber = "共有";
                    	}else if(allHave == 2){
                    		this.formData.systemKeyProducts[index].number = 0;
                    		this.formData.systemKeyProducts[index].productsNumber = 0;
                    	}else{//取消选中
                    		this.formData.systemKeyProducts[index].allHave = "";
                    		this.formData.systemKeyProducts[index].number = 0;
                    		this.formData.systemKeyProducts[index].productsNumber = "";
                    		this.formData.systemKeyProducts[index].fkNationalIsProducts = "";
                    		this.formData.systemKeyProducts[index].nUseProbability = 0;                     	}
                    },
                    
                    inputNumberChange:function(index){
                      this.formData.systemKeyProducts[index].allHave = 2;
                      this.formData.systemKeyProducts[index].productsNumber = this.formData.systemKeyProducts[index].number;
                    },
                    //获取数量信息
                    getNumberMethod: function(_self) {
                      _self.sysNumber.push({
                        "systemName" : "共有",
                        "systemCode" : "共有"
                      });
                      _self.sysNumber.push({
                        "systemName" : "无",
                        "systemCode" : "无"
                      });
                        _self.sysNumber.push({"systemName":1,"systemCode":1},{"systemName":2,"systemCode":2},{"systemName":3,"systemCode":3},{"systemName":4,"systemCode":4},{"systemName":5,"systemCode":5},{"systemName":6,"systemCode":6},{"systemName":7,"systemCode":7},{"systemName":8,"systemCode":8},{"systemName":9,"systemCode":9},{"systemName":10,"systemCode":10},{"systemName":11,"systemCode":11},{"systemName":12,"systemCode":12},{"systemName":13,"systemCode":13},{"systemName":14,"systemCode":14},{"systemName":15,"systemCode":15},{"systemName":16,"systemCode":16},{"systemName":17,"systemCode":17},{"systemName":18,"systemCode":18},{"systemName":19,"systemCode":19},{"systemName":20,"systemCode":20},{"systemName":21,"systemCode":21},{"systemName":22,"systemCode":22},{"systemName":23,"systemCode":23},{"systemName":24,"systemCode":24},{"systemName":25,"systemCode":25},{"systemName":26,"systemCode":26},{"systemName":27,"systemCode":27},{"systemName":28,"systemCode":28},{"systemName":29,"systemCode":29},{"systemName":30,"systemCode":30},{"systemName":31,"systemCode":31},{"systemName":32,"systemCode":32},{"systemName":33,"systemCode":33},{"systemName":34,"systemCode":34},{"systemName":35,"systemCode":35},{"systemName":36,"systemCode":36},{"systemName":37,"systemCode":37},{"systemName":38,"systemCode":38},{"systemName":39,"systemCode":39},{"systemName":40,"systemCode":40},{"systemName":41,"systemCode":41},{"systemName":42,"systemCode":42},{"systemName":43,"systemCode":43},{"systemName":44,"systemCode":44},{"systemName":45,"systemCode":45},{"systemName":46,"systemCode":46},{"systemName":47,"systemCode":47},{"systemName":48,"systemCode":48},{"systemName":49,"systemCode":49},{"systemName":50,"systemCode":50},{"systemName":51,"systemCode":51},{"systemName":52,"systemCode":52},{"systemName":53,"systemCode":53},{"systemName":54,"systemCode":54},{"systemName":55,"systemCode":55},{"systemName":56,"systemCode":56},{"systemName":57,"systemCode":57},{"systemName":58,"systemCode":58},{"systemName":59,"systemCode":59},{"systemName":60,"systemCode":60},{"systemName":61,"systemCode":61},{"systemName":62,"systemCode":62},{"systemName":63,"systemCode":63},{"systemName":64,"systemCode":64},{"systemName":65,"systemCode":65},{"systemName":66,"systemCode":66},{"systemName":67,"systemCode":67},{"systemName":68,"systemCode":68},{"systemName":69,"systemCode":69},{"systemName":70,"systemCode":70},{"systemName":71,"systemCode":71},{"systemName":72,"systemCode":72},{"systemName":73,"systemCode":73},{"systemName":74,"systemCode":74},{"systemName":75,"systemCode":75},{"systemName":76,"systemCode":76},{"systemName":77,"systemCode":77},{"systemName":78,"systemCode":78},{"systemName":79,"systemCode":79},{"systemName":80,"systemCode":80},{"systemName":81,"systemCode":81},{"systemName":82,"systemCode":82},{"systemName":83,"systemCode":83},{"systemName":84,"systemCode":84},{"systemName":85,"systemCode":85},{"systemName":86,"systemCode":86},{"systemName":87,"systemCode":87},{"systemName":88,"systemCode":88},{"systemName":89,"systemCode":89},{"systemName":90,"systemCode":90},{"systemName":91,"systemCode":91},{"systemName":92,"systemCode":92},{"systemName":93,"systemCode":93},{"systemName":94,"systemCode":94},{"systemName":95,"systemCode":95},{"systemName":96,"systemCode":96},{"systemName":97,"systemCode":97},{"systemName":98,"systemCode":98},{"systemName":99,"systemCode":99},{"systemName":100,"systemCode":100},{"systemName":101,"systemCode":101},{"systemName":102,"systemCode":102},{"systemName":103,"systemCode":103},{"systemName":104,"systemCode":104},{"systemName":105,"systemCode":105},{"systemName":106,"systemCode":106},{"systemName":107,"systemCode":107},{"systemName":108,"systemCode":108},{"systemName":109,"systemCode":109},{"systemName":110,"systemCode":110},{"systemName":111,"systemCode":111},{"systemName":112,"systemCode":112},{"systemName":113,"systemCode":113},{"systemName":114,"systemCode":114},{"systemName":115,"systemCode":115},{"systemName":116,"systemCode":116},{"systemName":117,"systemCode":117},{"systemName":118,"systemCode":118},{"systemName":119,"systemCode":119},{"systemName":120,"systemCode":120},{"systemName":121,"systemCode":121},{"systemName":122,"systemCode":122},{"systemName":123,"systemCode":123},{"systemName":124,"systemCode":124},{"systemName":125,"systemCode":125},{"systemName":126,"systemCode":126},{"systemName":127,"systemCode":127},{"systemName":128,"systemCode":128},{"systemName":129,"systemCode":129},{"systemName":130,"systemCode":130},{"systemName":131,"systemCode":131},{"systemName":132,"systemCode":132},{"systemName":133,"systemCode":133},{"systemName":134,"systemCode":134},{"systemName":135,"systemCode":135},{"systemName":136,"systemCode":136},{"systemName":137,"systemCode":137},{"systemName":138,"systemCode":138},{"systemName":139,"systemCode":139},{"systemName":140,"systemCode":140},{"systemName":141,"systemCode":141},{"systemName":142,"systemCode":142},{"systemName":143,"systemCode":143},{"systemName":144,"systemCode":144},{"systemName":145,"systemCode":145},{"systemName":146,"systemCode":146},{"systemName":147,"systemCode":147},{"systemName":148,"systemCode":148},{"systemName":149,"systemCode":149},{"systemName":150,"systemCode":150},{"systemName":151,"systemCode":151},{"systemName":152,"systemCode":152},{"systemName":153,"systemCode":153},{"systemName":154,"systemCode":154},{"systemName":155,"systemCode":155},{"systemName":156,"systemCode":156},{"systemName":157,"systemCode":157},{"systemName":158,"systemCode":158},{"systemName":159,"systemCode":159},{"systemName":160,"systemCode":160},{"systemName":161,"systemCode":161},{"systemName":162,"systemCode":162},{"systemName":163,"systemCode":163},{"systemName":164,"systemCode":164},{"systemName":165,"systemCode":165},{"systemName":166,"systemCode":166},{"systemName":167,"systemCode":167},{"systemName":168,"systemCode":168},{"systemName":169,"systemCode":169},{"systemName":170,"systemCode":170},{"systemName":171,"systemCode":171},{"systemName":172,"systemCode":172},{"systemName":173,"systemCode":173},{"systemName":174,"systemCode":174},{"systemName":175,"systemCode":175},{"systemName":176,"systemCode":176},{"systemName":177,"systemCode":177},{"systemName":178,"systemCode":178},{"systemName":179,"systemCode":179},{"systemName":180,"systemCode":180},{"systemName":181,"systemCode":181},{"systemName":182,"systemCode":182},{"systemName":183,"systemCode":183},{"systemName":184,"systemCode":184},{"systemName":185,"systemCode":185},{"systemName":186,"systemCode":186},{"systemName":187,"systemCode":187},{"systemName":188,"systemCode":188},{"systemName":189,"systemCode":189},{"systemName":190,"systemCode":190},{"systemName":191,"systemCode":191},{"systemName":192,"systemCode":192},{"systemName":193,"systemCode":193},{"systemName":194,"systemCode":194},{"systemName":195,"systemCode":195},{"systemName":196,"systemCode":196},{"systemName":197,"systemCode":197},{"systemName":198,"systemCode":198},{"systemName":199,"systemCode":199},{"systemName":200,"systemCode":200},{"systemName":201,"systemCode":201},{"systemName":202,"systemCode":202},{"systemName":203,"systemCode":203},{"systemName":204,"systemCode":204},{"systemName":205,"systemCode":205},{"systemName":206,"systemCode":206},{"systemName":207,"systemCode":207},{"systemName":208,"systemCode":208},{"systemName":209,"systemCode":209},{"systemName":210,"systemCode":210},{"systemName":211,"systemCode":211},{"systemName":212,"systemCode":212},{"systemName":213,"systemCode":213},{"systemName":214,"systemCode":214},{"systemName":215,"systemCode":215},{"systemName":216,"systemCode":216},{"systemName":217,"systemCode":217},{"systemName":218,"systemCode":218},{"systemName":219,"systemCode":219},{"systemName":220,"systemCode":220},{"systemName":221,"systemCode":221},{"systemName":222,"systemCode":222},{"systemName":223,"systemCode":223},{"systemName":224,"systemCode":224},{"systemName":225,"systemCode":225},{"systemName":226,"systemCode":226},{"systemName":227,"systemCode":227},{"systemName":228,"systemCode":228},{"systemName":229,"systemCode":229},{"systemName":230,"systemCode":230},{"systemName":231,"systemCode":231},{"systemName":232,"systemCode":232},{"systemName":233,"systemCode":233},{"systemName":234,"systemCode":234},{"systemName":235,"systemCode":235},{"systemName":236,"systemCode":236},{"systemName":237,"systemCode":237},{"systemName":238,"systemCode":238},{"systemName":239,"systemCode":239},{"systemName":240,"systemCode":240},{"systemName":241,"systemCode":241},{"systemName":242,"systemCode":242},{"systemName":243,"systemCode":243},{"systemName":244,"systemCode":244},{"systemName":245,"systemCode":245},{"systemName":246,"systemCode":246},{"systemName":247,"systemCode":247},{"systemName":248,"systemCode":248},{"systemName":249,"systemCode":249},{"systemName":250,"systemCode":250},{"systemName":251,"systemCode":251},{"systemName":252,"systemCode":252},{"systemName":253,"systemCode":253},{"systemName":254,"systemCode":254},{"systemName":255,"systemCode":255},{"systemName":256,"systemCode":256},{"systemName":257,"systemCode":257},{"systemName":258,"systemCode":258},{"systemName":259,"systemCode":259},{"systemName":260,"systemCode":260},{"systemName":261,"systemCode":261},{"systemName":262,"systemCode":262},{"systemName":263,"systemCode":263},{"systemName":264,"systemCode":264},{"systemName":265,"systemCode":265},{"systemName":266,"systemCode":266},{"systemName":267,"systemCode":267},{"systemName":268,"systemCode":268},{"systemName":269,"systemCode":269},{"systemName":270,"systemCode":270},{"systemName":271,"systemCode":271},{"systemName":272,"systemCode":272},{"systemName":273,"systemCode":273},{"systemName":274,"systemCode":274},{"systemName":275,"systemCode":275},{"systemName":276,"systemCode":276},{"systemName":277,"systemCode":277},{"systemName":278,"systemCode":278},{"systemName":279,"systemCode":279},{"systemName":280,"systemCode":280},{"systemName":281,"systemCode":281},{"systemName":282,"systemCode":282},{"systemName":283,"systemCode":283},{"systemName":284,"systemCode":284},{"systemName":285,"systemCode":285},{"systemName":286,"systemCode":286},{"systemName":287,"systemCode":287},{"systemName":288,"systemCode":288},{"systemName":289,"systemCode":289},{"systemName":290,"systemCode":290},{"systemName":291,"systemCode":291},{"systemName":292,"systemCode":292},{"systemName":293,"systemCode":293},{"systemName":294,"systemCode":294},{"systemName":295,"systemCode":295},{"systemName":296,"systemCode":296},{"systemName":297,"systemCode":297},{"systemName":298,"systemCode":298},{"systemName":299,"systemCode":299},{"systemName":300,"systemCode":300},{"systemName":301,"systemCode":301},{"systemName":302,"systemCode":302},{"systemName":303,"systemCode":303},{"systemName":304,"systemCode":304},{"systemName":305,"systemCode":305},{"systemName":306,"systemCode":306},{"systemName":307,"systemCode":307},{"systemName":308,"systemCode":308},{"systemName":309,"systemCode":309},{"systemName":310,"systemCode":310},{"systemName":311,"systemCode":311},{"systemName":312,"systemCode":312},{"systemName":313,"systemCode":313},{"systemName":314,"systemCode":314},{"systemName":315,"systemCode":315},{"systemName":316,"systemCode":316},{"systemName":317,"systemCode":317},{"systemName":318,"systemCode":318},{"systemName":319,"systemCode":319},{"systemName":320,"systemCode":320},{"systemName":321,"systemCode":321},{"systemName":322,"systemCode":322},{"systemName":323,"systemCode":323},{"systemName":324,"systemCode":324},{"systemName":325,"systemCode":325},{"systemName":326,"systemCode":326},{"systemName":327,"systemCode":327},{"systemName":328,"systemCode":328},{"systemName":329,"systemCode":329},{"systemName":330,"systemCode":330},{"systemName":331,"systemCode":331},{"systemName":332,"systemCode":332},{"systemName":333,"systemCode":333},{"systemName":334,"systemCode":334},{"systemName":335,"systemCode":335},{"systemName":336,"systemCode":336},{"systemName":337,"systemCode":337},{"systemName":338,"systemCode":338},{"systemName":339,"systemCode":339},{"systemName":340,"systemCode":340},{"systemName":341,"systemCode":341},{"systemName":342,"systemCode":342},{"systemName":343,"systemCode":343},{"systemName":344,"systemCode":344},{"systemName":345,"systemCode":345},{"systemName":346,"systemCode":346},{"systemName":347,"systemCode":347},{"systemName":348,"systemCode":348},{"systemName":349,"systemCode":349},{"systemName":350,"systemCode":350},{"systemName":351,"systemCode":351},{"systemName":352,"systemCode":352},{"systemName":353,"systemCode":353},{"systemName":354,"systemCode":354},{"systemName":355,"systemCode":355},{"systemName":356,"systemCode":356},{"systemName":357,"systemCode":357},{"systemName":358,"systemCode":358},{"systemName":359,"systemCode":359},{"systemName":360,"systemCode":360},{"systemName":361,"systemCode":361},{"systemName":362,"systemCode":362},{"systemName":363,"systemCode":363},{"systemName":364,"systemCode":364},{"systemName":365,"systemCode":365},{"systemName":366,"systemCode":366},{"systemName":367,"systemCode":367},{"systemName":368,"systemCode":368},{"systemName":369,"systemCode":369},{"systemName":370,"systemCode":370},{"systemName":371,"systemCode":371},{"systemName":372,"systemCode":372},{"systemName":373,"systemCode":373},{"systemName":374,"systemCode":374},{"systemName":375,"systemCode":375},{"systemName":376,"systemCode":376},{"systemName":377,"systemCode":377},{"systemName":378,"systemCode":378},{"systemName":379,"systemCode":379},{"systemName":380,"systemCode":380},{"systemName":381,"systemCode":381},{"systemName":382,"systemCode":382},{"systemName":383,"systemCode":383},{"systemName":384,"systemCode":384},{"systemName":385,"systemCode":385},{"systemName":386,"systemCode":386},{"systemName":387,"systemCode":387},{"systemName":388,"systemCode":388},{"systemName":389,"systemCode":389},{"systemName":390,"systemCode":390},{"systemName":391,"systemCode":391},{"systemName":392,"systemCode":392},{"systemName":393,"systemCode":393},{"systemName":394,"systemCode":394},{"systemName":395,"systemCode":395},{"systemName":396,"systemCode":396},{"systemName":397,"systemCode":397},{"systemName":398,"systemCode":398},{"systemName":399,"systemCode":399},{"systemName":400,"systemCode":400},{"systemName":401,"systemCode":401},{"systemName":402,"systemCode":402},{"systemName":403,"systemCode":403},{"systemName":404,"systemCode":404},{"systemName":405,"systemCode":405},{"systemName":406,"systemCode":406},{"systemName":407,"systemCode":407},{"systemName":408,"systemCode":408},{"systemName":409,"systemCode":409},{"systemName":410,"systemCode":410},{"systemName":411,"systemCode":411},{"systemName":412,"systemCode":412},{"systemName":413,"systemCode":413},{"systemName":414,"systemCode":414},{"systemName":415,"systemCode":415},{"systemName":416,"systemCode":416},{"systemName":417,"systemCode":417},{"systemName":418,"systemCode":418},{"systemName":419,"systemCode":419},{"systemName":420,"systemCode":420},{"systemName":421,"systemCode":421},{"systemName":422,"systemCode":422},{"systemName":423,"systemCode":423},{"systemName":424,"systemCode":424},{"systemName":425,"systemCode":425},{"systemName":426,"systemCode":426},{"systemName":427,"systemCode":427},{"systemName":428,"systemCode":428},{"systemName":429,"systemCode":429},{"systemName":430,"systemCode":430},{"systemName":431,"systemCode":431},{"systemName":432,"systemCode":432},{"systemName":433,"systemCode":433},{"systemName":434,"systemCode":434},{"systemName":435,"systemCode":435},{"systemName":436,"systemCode":436},{"systemName":437,"systemCode":437},{"systemName":438,"systemCode":438},{"systemName":439,"systemCode":439},{"systemName":440,"systemCode":440},{"systemName":441,"systemCode":441},{"systemName":442,"systemCode":442},{"systemName":443,"systemCode":443},{"systemName":444,"systemCode":444},{"systemName":445,"systemCode":445},{"systemName":446,"systemCode":446},{"systemName":447,"systemCode":447},{"systemName":448,"systemCode":448},{"systemName":449,"systemCode":449},{"systemName":450,"systemCode":450},{"systemName":451,"systemCode":451},{"systemName":452,"systemCode":452},{"systemName":453,"systemCode":453},{"systemName":454,"systemCode":454},{"systemName":455,"systemCode":455},{"systemName":456,"systemCode":456},{"systemName":457,"systemCode":457},{"systemName":458,"systemCode":458},{"systemName":459,"systemCode":459},{"systemName":460,"systemCode":460},{"systemName":461,"systemCode":461},{"systemName":462,"systemCode":462},{"systemName":463,"systemCode":463},{"systemName":464,"systemCode":464},{"systemName":465,"systemCode":465},{"systemName":466,"systemCode":466},{"systemName":467,"systemCode":467},{"systemName":468,"systemCode":468},{"systemName":469,"systemCode":469},{"systemName":470,"systemCode":470},{"systemName":471,"systemCode":471},{"systemName":472,"systemCode":472},{"systemName":473,"systemCode":473},{"systemName":474,"systemCode":474},{"systemName":475,"systemCode":475},{"systemName":476,"systemCode":476},{"systemName":477,"systemCode":477},{"systemName":478,"systemCode":478},{"systemName":479,"systemCode":479},{"systemName":480,"systemCode":480},{"systemName":481,"systemCode":481},{"systemName":482,"systemCode":482},{"systemName":483,"systemCode":483},{"systemName":484,"systemCode":484},{"systemName":485,"systemCode":485},{"systemName":486,"systemCode":486},{"systemName":487,"systemCode":487},{"systemName":488,"systemCode":488},{"systemName":489,"systemCode":489},{"systemName":490,"systemCode":490},{"systemName":491,"systemCode":491},{"systemName":492,"systemCode":492},{"systemName":493,"systemCode":493},{"systemName":494,"systemCode":494},{"systemName":495,"systemCode":495},{"systemName":496,"systemCode":496},{"systemName":497,"systemCode":497},{"systemName":498,"systemCode":498},{"systemName":499,"systemCode":499},{"systemName":500,"systemCode":500},{"systemName":501,"systemCode":501},{"systemName":502,"systemCode":502},{"systemName":503,"systemCode":503},{"systemName":504,"systemCode":504},{"systemName":505,"systemCode":505},{"systemName":506,"systemCode":506},{"systemName":507,"systemCode":507},{"systemName":508,"systemCode":508},{"systemName":509,"systemCode":509},{"systemName":510,"systemCode":510},{"systemName":511,"systemCode":511},{"systemName":512,"systemCode":512},{"systemName":513,"systemCode":513},{"systemName":514,"systemCode":514},{"systemName":515,"systemCode":515},{"systemName":516,"systemCode":516},{"systemName":517,"systemCode":517},{"systemName":518,"systemCode":518},{"systemName":519,"systemCode":519},{"systemName":520,"systemCode":520},{"systemName":521,"systemCode":521},{"systemName":522,"systemCode":522},{"systemName":523,"systemCode":523},{"systemName":524,"systemCode":524},{"systemName":525,"systemCode":525},{"systemName":526,"systemCode":526},{"systemName":527,"systemCode":527},{"systemName":528,"systemCode":528},{"systemName":529,"systemCode":529},{"systemName":530,"systemCode":530},{"systemName":531,"systemCode":531},{"systemName":532,"systemCode":532},{"systemName":533,"systemCode":533},{"systemName":534,"systemCode":534},{"systemName":535,"systemCode":535},{"systemName":536,"systemCode":536},{"systemName":537,"systemCode":537},{"systemName":538,"systemCode":538},{"systemName":539,"systemCode":539},{"systemName":540,"systemCode":540},{"systemName":541,"systemCode":541},{"systemName":542,"systemCode":542},{"systemName":543,"systemCode":543},{"systemName":544,"systemCode":544},{"systemName":545,"systemCode":545},{"systemName":546,"systemCode":546},{"systemName":547,"systemCode":547},{"systemName":548,"systemCode":548},{"systemName":549,"systemCode":549},{"systemName":550,"systemCode":550},{"systemName":551,"systemCode":551},{"systemName":552,"systemCode":552},{"systemName":553,"systemCode":553},{"systemName":554,"systemCode":554},{"systemName":555,"systemCode":555},{"systemName":556,"systemCode":556},{"systemName":557,"systemCode":557},{"systemName":558,"systemCode":558},{"systemName":559,"systemCode":559},{"systemName":560,"systemCode":560},{"systemName":561,"systemCode":561},{"systemName":562,"systemCode":562},{"systemName":563,"systemCode":563},{"systemName":564,"systemCode":564},{"systemName":565,"systemCode":565},{"systemName":566,"systemCode":566},{"systemName":567,"systemCode":567},{"systemName":568,"systemCode":568},{"systemName":569,"systemCode":569},{"systemName":570,"systemCode":570},{"systemName":571,"systemCode":571},{"systemName":572,"systemCode":572},{"systemName":573,"systemCode":573},{"systemName":574,"systemCode":574},{"systemName":575,"systemCode":575},{"systemName":576,"systemCode":576},{"systemName":577,"systemCode":577},{"systemName":578,"systemCode":578},{"systemName":579,"systemCode":579},{"systemName":580,"systemCode":580},{"systemName":581,"systemCode":581},{"systemName":582,"systemCode":582},{"systemName":583,"systemCode":583},{"systemName":584,"systemCode":584},{"systemName":585,"systemCode":585},{"systemName":586,"systemCode":586},{"systemName":587,"systemCode":587},{"systemName":588,"systemCode":588},{"systemName":589,"systemCode":589},{"systemName":590,"systemCode":590},{"systemName":591,"systemCode":591},{"systemName":592,"systemCode":592},{"systemName":593,"systemCode":593},{"systemName":594,"systemCode":594},{"systemName":595,"systemCode":595},{"systemName":596,"systemCode":596},{"systemName":597,"systemCode":597},{"systemName":598,"systemCode":598},{"systemName":599,"systemCode":599},{"systemName":600,"systemCode":600},{"systemName":601,"systemCode":601},{"systemName":602,"systemCode":602},{"systemName":603,"systemCode":603},{"systemName":604,"systemCode":604},{"systemName":605,"systemCode":605},{"systemName":606,"systemCode":606},{"systemName":607,"systemCode":607},{"systemName":608,"systemCode":608},{"systemName":609,"systemCode":609},{"systemName":610,"systemCode":610},{"systemName":611,"systemCode":611},{"systemName":612,"systemCode":612},{"systemName":613,"systemCode":613},{"systemName":614,"systemCode":614},{"systemName":615,"systemCode":615},{"systemName":616,"systemCode":616},{"systemName":617,"systemCode":617},{"systemName":618,"systemCode":618},{"systemName":619,"systemCode":619},{"systemName":620,"systemCode":620},{"systemName":621,"systemCode":621},{"systemName":622,"systemCode":622},{"systemName":623,"systemCode":623},{"systemName":624,"systemCode":624},{"systemName":625,"systemCode":625},{"systemName":626,"systemCode":626},{"systemName":627,"systemCode":627},{"systemName":628,"systemCode":628},{"systemName":629,"systemCode":629},{"systemName":630,"systemCode":630},{"systemName":631,"systemCode":631},{"systemName":632,"systemCode":632},{"systemName":633,"systemCode":633},{"systemName":634,"systemCode":634},{"systemName":635,"systemCode":635},{"systemName":636,"systemCode":636},{"systemName":637,"systemCode":637},{"systemName":638,"systemCode":638},{"systemName":639,"systemCode":639},{"systemName":640,"systemCode":640},{"systemName":641,"systemCode":641},{"systemName":642,"systemCode":642},{"systemName":643,"systemCode":643},{"systemName":644,"systemCode":644},{"systemName":645,"systemCode":645},{"systemName":646,"systemCode":646},{"systemName":647,"systemCode":647},{"systemName":648,"systemCode":648},{"systemName":649,"systemCode":649},{"systemName":650,"systemCode":650},{"systemName":651,"systemCode":651},{"systemName":652,"systemCode":652},{"systemName":653,"systemCode":653},{"systemName":654,"systemCode":654},{"systemName":655,"systemCode":655},{"systemName":656,"systemCode":656},{"systemName":657,"systemCode":657},{"systemName":658,"systemCode":658},{"systemName":659,"systemCode":659},{"systemName":660,"systemCode":660},{"systemName":661,"systemCode":661},{"systemName":662,"systemCode":662},{"systemName":663,"systemCode":663},{"systemName":664,"systemCode":664},{"systemName":665,"systemCode":665},{"systemName":666,"systemCode":666},{"systemName":667,"systemCode":667},{"systemName":668,"systemCode":668},{"systemName":669,"systemCode":669},{"systemName":670,"systemCode":670},{"systemName":671,"systemCode":671},{"systemName":672,"systemCode":672},{"systemName":673,"systemCode":673},{"systemName":674,"systemCode":674},{"systemName":675,"systemCode":675},{"systemName":676,"systemCode":676},{"systemName":677,"systemCode":677},{"systemName":678,"systemCode":678},{"systemName":679,"systemCode":679},{"systemName":680,"systemCode":680},{"systemName":681,"systemCode":681},{"systemName":682,"systemCode":682},{"systemName":683,"systemCode":683},{"systemName":684,"systemCode":684},{"systemName":685,"systemCode":685},{"systemName":686,"systemCode":686},{"systemName":687,"systemCode":687},{"systemName":688,"systemCode":688},{"systemName":689,"systemCode":689},{"systemName":690,"systemCode":690},{"systemName":691,"systemCode":691},{"systemName":692,"systemCode":692},{"systemName":693,"systemCode":693},{"systemName":694,"systemCode":694},{"systemName":695,"systemCode":695},{"systemName":696,"systemCode":696},{"systemName":697,"systemCode":697},{"systemName":698,"systemCode":698},{"systemName":699,"systemCode":699},{"systemName":700,"systemCode":700},{"systemName":701,"systemCode":701},{"systemName":702,"systemCode":702},{"systemName":703,"systemCode":703},{"systemName":704,"systemCode":704},{"systemName":705,"systemCode":705},{"systemName":706,"systemCode":706},{"systemName":707,"systemCode":707},{"systemName":708,"systemCode":708},{"systemName":709,"systemCode":709},{"systemName":710,"systemCode":710},{"systemName":711,"systemCode":711},{"systemName":712,"systemCode":712},{"systemName":713,"systemCode":713},{"systemName":714,"systemCode":714},{"systemName":715,"systemCode":715},{"systemName":716,"systemCode":716},{"systemName":717,"systemCode":717},{"systemName":718,"systemCode":718},{"systemName":719,"systemCode":719},{"systemName":720,"systemCode":720},{"systemName":721,"systemCode":721},{"systemName":722,"systemCode":722},{"systemName":723,"systemCode":723},{"systemName":724,"systemCode":724},{"systemName":725,"systemCode":725},{"systemName":726,"systemCode":726},{"systemName":727,"systemCode":727},{"systemName":728,"systemCode":728},{"systemName":729,"systemCode":729},{"systemName":730,"systemCode":730},{"systemName":731,"systemCode":731},{"systemName":732,"systemCode":732},{"systemName":733,"systemCode":733},{"systemName":734,"systemCode":734},{"systemName":735,"systemCode":735},{"systemName":736,"systemCode":736},{"systemName":737,"systemCode":737},{"systemName":738,"systemCode":738},{"systemName":739,"systemCode":739},{"systemName":740,"systemCode":740},{"systemName":741,"systemCode":741},{"systemName":742,"systemCode":742},{"systemName":743,"systemCode":743},{"systemName":744,"systemCode":744},{"systemName":745,"systemCode":745},{"systemName":746,"systemCode":746},{"systemName":747,"systemCode":747},{"systemName":748,"systemCode":748},{"systemName":749,"systemCode":749},{"systemName":750,"systemCode":750},{"systemName":751,"systemCode":751},{"systemName":752,"systemCode":752},{"systemName":753,"systemCode":753},{"systemName":754,"systemCode":754},{"systemName":755,"systemCode":755},{"systemName":756,"systemCode":756},{"systemName":757,"systemCode":757},{"systemName":758,"systemCode":758},{"systemName":759,"systemCode":759},{"systemName":760,"systemCode":760},{"systemName":761,"systemCode":761},{"systemName":762,"systemCode":762},{"systemName":763,"systemCode":763},{"systemName":764,"systemCode":764},{"systemName":765,"systemCode":765},{"systemName":766,"systemCode":766},{"systemName":767,"systemCode":767},{"systemName":768,"systemCode":768},{"systemName":769,"systemCode":769},{"systemName":770,"systemCode":770},{"systemName":771,"systemCode":771},{"systemName":772,"systemCode":772},{"systemName":773,"systemCode":773},{"systemName":774,"systemCode":774},{"systemName":775,"systemCode":775},{"systemName":776,"systemCode":776},{"systemName":777,"systemCode":777},{"systemName":778,"systemCode":778},{"systemName":779,"systemCode":779},{"systemName":780,"systemCode":780},{"systemName":781,"systemCode":781},{"systemName":782,"systemCode":782},{"systemName":783,"systemCode":783},{"systemName":784,"systemCode":784},{"systemName":785,"systemCode":785},{"systemName":786,"systemCode":786},{"systemName":787,"systemCode":787},{"systemName":788,"systemCode":788},{"systemName":789,"systemCode":789},{"systemName":790,"systemCode":790},{"systemName":791,"systemCode":791},{"systemName":792,"systemCode":792},{"systemName":793,"systemCode":793},{"systemName":794,"systemCode":794},{"systemName":795,"systemCode":795},{"systemName":796,"systemCode":796},{"systemName":797,"systemCode":797},{"systemName":798,"systemCode":798},{"systemName":799,"systemCode":799},{"systemName":800,"systemCode":800},{"systemName":801,"systemCode":801},{"systemName":802,"systemCode":802},{"systemName":803,"systemCode":803},{"systemName":804,"systemCode":804},{"systemName":805,"systemCode":805},{"systemName":806,"systemCode":806},{"systemName":807,"systemCode":807},{"systemName":808,"systemCode":808},{"systemName":809,"systemCode":809},{"systemName":810,"systemCode":810},{"systemName":811,"systemCode":811},{"systemName":812,"systemCode":812},{"systemName":813,"systemCode":813},{"systemName":814,"systemCode":814},{"systemName":815,"systemCode":815},{"systemName":816,"systemCode":816},{"systemName":817,"systemCode":817},{"systemName":818,"systemCode":818},{"systemName":819,"systemCode":819},{"systemName":820,"systemCode":820},{"systemName":821,"systemCode":821},{"systemName":822,"systemCode":822},{"systemName":823,"systemCode":823},{"systemName":824,"systemCode":824},{"systemName":825,"systemCode":825},{"systemName":826,"systemCode":826},{"systemName":827,"systemCode":827},{"systemName":828,"systemCode":828},{"systemName":829,"systemCode":829},{"systemName":830,"systemCode":830},{"systemName":831,"systemCode":831},{"systemName":832,"systemCode":832},{"systemName":833,"systemCode":833},{"systemName":834,"systemCode":834},{"systemName":835,"systemCode":835},{"systemName":836,"systemCode":836},{"systemName":837,"systemCode":837},{"systemName":838,"systemCode":838},{"systemName":839,"systemCode":839},{"systemName":840,"systemCode":840},{"systemName":841,"systemCode":841},{"systemName":842,"systemCode":842},{"systemName":843,"systemCode":843},{"systemName":844,"systemCode":844},{"systemName":845,"systemCode":845},{"systemName":846,"systemCode":846},{"systemName":847,"systemCode":847},{"systemName":848,"systemCode":848},{"systemName":849,"systemCode":849},{"systemName":850,"systemCode":850},{"systemName":851,"systemCode":851},{"systemName":852,"systemCode":852},{"systemName":853,"systemCode":853},{"systemName":854,"systemCode":854},{"systemName":855,"systemCode":855},{"systemName":856,"systemCode":856},{"systemName":857,"systemCode":857},{"systemName":858,"systemCode":858},{"systemName":859,"systemCode":859},{"systemName":860,"systemCode":860},{"systemName":861,"systemCode":861},{"systemName":862,"systemCode":862},{"systemName":863,"systemCode":863},{"systemName":864,"systemCode":864},{"systemName":865,"systemCode":865},{"systemName":866,"systemCode":866},{"systemName":867,"systemCode":867},{"systemName":868,"systemCode":868},{"systemName":869,"systemCode":869},{"systemName":870,"systemCode":870},{"systemName":871,"systemCode":871},{"systemName":872,"systemCode":872},{"systemName":873,"systemCode":873},{"systemName":874,"systemCode":874},{"systemName":875,"systemCode":875},{"systemName":876,"systemCode":876},{"systemName":877,"systemCode":877},{"systemName":878,"systemCode":878},{"systemName":879,"systemCode":879},{"systemName":880,"systemCode":880},{"systemName":881,"systemCode":881},{"systemName":882,"systemCode":882},{"systemName":883,"systemCode":883},{"systemName":884,"systemCode":884},{"systemName":885,"systemCode":885},{"systemName":886,"systemCode":886},{"systemName":887,"systemCode":887},{"systemName":888,"systemCode":888},{"systemName":889,"systemCode":889},{"systemName":890,"systemCode":890},{"systemName":891,"systemCode":891},{"systemName":892,"systemCode":892},{"systemName":893,"systemCode":893},{"systemName":894,"systemCode":894},{"systemName":895,"systemCode":895},{"systemName":896,"systemCode":896},{"systemName":897,"systemCode":897},{"systemName":898,"systemCode":898},{"systemName":899,"systemCode":899},{"systemName":900,"systemCode":900},{"systemName":901,"systemCode":901},{"systemName":902,"systemCode":902},{"systemName":903,"systemCode":903},{"systemName":904,"systemCode":904},{"systemName":905,"systemCode":905},{"systemName":906,"systemCode":906},{"systemName":907,"systemCode":907},{"systemName":908,"systemCode":908},{"systemName":909,"systemCode":909},{"systemName":910,"systemCode":910},{"systemName":911,"systemCode":911},{"systemName":912,"systemCode":912},{"systemName":913,"systemCode":913},{"systemName":914,"systemCode":914},{"systemName":915,"systemCode":915},{"systemName":916,"systemCode":916},{"systemName":917,"systemCode":917},{"systemName":918,"systemCode":918},{"systemName":919,"systemCode":919},{"systemName":920,"systemCode":920},{"systemName":921,"systemCode":921},{"systemName":922,"systemCode":922},{"systemName":923,"systemCode":923},{"systemName":924,"systemCode":924},{"systemName":925,"systemCode":925},{"systemName":926,"systemCode":926},{"systemName":927,"systemCode":927},{"systemName":928,"systemCode":928},{"systemName":929,"systemCode":929},{"systemName":930,"systemCode":930},{"systemName":931,"systemCode":931},{"systemName":932,"systemCode":932},{"systemName":933,"systemCode":933},{"systemName":934,"systemCode":934},{"systemName":935,"systemCode":935},{"systemName":936,"systemCode":936},{"systemName":937,"systemCode":937},{"systemName":938,"systemCode":938},{"systemName":939,"systemCode":939},{"systemName":940,"systemCode":940},{"systemName":941,"systemCode":941},{"systemName":942,"systemCode":942},{"systemName":943,"systemCode":943},{"systemName":944,"systemCode":944},{"systemName":945,"systemCode":945},{"systemName":946,"systemCode":946},{"systemName":947,"systemCode":947},{"systemName":948,"systemCode":948},{"systemName":949,"systemCode":949},{"systemName":950,"systemCode":950},{"systemName":951,"systemCode":951},{"systemName":952,"systemCode":952},{"systemName":953,"systemCode":953},{"systemName":954,"systemCode":954},{"systemName":955,"systemCode":955},{"systemName":956,"systemCode":956},{"systemName":957,"systemCode":957},{"systemName":958,"systemCode":958},{"systemName":959,"systemCode":959},{"systemName":960,"systemCode":960},{"systemName":961,"systemCode":961},{"systemName":962,"systemCode":962},{"systemName":963,"systemCode":963},{"systemName":964,"systemCode":964},{"systemName":965,"systemCode":965},{"systemName":966,"systemCode":966},{"systemName":967,"systemCode":967},{"systemName":968,"systemCode":968},{"systemName":969,"systemCode":969},{"systemName":970,"systemCode":970},{"systemName":971,"systemCode":971},{"systemName":972,"systemCode":972},{"systemName":973,"systemCode":973},{"systemName":974,"systemCode":974},{"systemName":975,"systemCode":975},{"systemName":976,"systemCode":976},{"systemName":977,"systemCode":977},{"systemName":978,"systemCode":978},{"systemName":979,"systemCode":979},{"systemName":980,"systemCode":980},{"systemName":981,"systemCode":981},{"systemName":982,"systemCode":982},{"systemName":983,"systemCode":983},{"systemName":984,"systemCode":984},{"systemName":985,"systemCode":985},{"systemName":986,"systemCode":986},{"systemName":987,"systemCode":987},{"systemName":988,"systemCode":988},{"systemName":989,"systemCode":989},{"systemName":990,"systemCode":990},{"systemName":991,"systemCode":991},{"systemName":992,"systemCode":992},{"systemName":993,"systemCode":993},{"systemName":994,"systemCode":994},{"systemName":995,"systemCode":995},{"systemName":996,"systemCode":996},{"systemName":997,"systemCode":997},{"systemName":998,"systemCode":998},{"systemName":999,"systemCode":999});
                      /*for (var i = 0; i < 10; i++) {
                        _self.sysNumber.push({
                          "systemName" : i,
                          "systemCode" : i
                        });
                      }*/
                    },
                    //获取是否使用国产品信息
                    getNationalProductsMethod: function(_self) {
//                      ajaxMethod(_self, 'post',
//                          'systemCode/querySystemCodeForKeySystemCode', true,
//                          '{"codeType":"9"}', 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getNationalProductsSuccessMethod);
//                      var products = JSON.parse([{"codeName":"请选择","systemCode":""},{"codeName":"全部使用","systemCode":1},{"codeName":"全部未使用","systemCode":2},{"codeName":"部分使用","systemCode":3}]);
//                      _self.sysNationalProducts = products;
                      _self.sysNationalProducts = [{codeName:'请选择',systemCode:''},{codeName:'全部使用',systemCode:'1'},{codeName:'全部未使用',systemCode:'2'},{codeName:'部分使用',systemCode:'3'}];
                    },
                    // 获取是否使用国产品信息
                    getNationalProductsSuccessMethod : function(_self, responseData) {
                      _self.sysNationalProducts = responseData.data;
                    },
                    //获取服务责任方类型
                    getResponsibleMethod: function(_self) {
//                      ajaxMethod(_self, 'post',
//                          'systemCode/querySystemCodeForKeySystemCode', true,
//                          '{"codeType":"15"}', 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getResponsibleSuccessMethod);
                      
                      var responsible = '[{"codeName":"请选择","systemCode":0},{"codeName":"本行业（单位）","systemCode":1},{"codeName":"国内其他服务商","systemCode":2},{"codeName":"国外服务商","systemCode":3}]'
                      _self.sysResponsible = JSON.parse(responsible);
                      _self.sysIs = JSON.parse('[{"codeName":"请选择","systemCode":0},{"codeName":"是","systemCode":1},{"codeName":"否","systemCode":2}]');
                    },
                    // 获取服务责任方类型
                    getResponsibleSuccessMethod : function(_self, responseData) {
                      _self.sysResponsible = responseData.data;
                    },
                    // 获取服务类型信息
                    getServiceTypeMethod : function(_self) {
                      ajaxMethod(_self, 'post',
                          'systemCode/querySystemCodeForKeySystemCode', true,
                          '{"codeType":"14"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getServiceTypeSuccessMethod);
                    },
                    // 获取服务类型信息成功
                    getServiceTypeSuccessMethod : function(_self, responseData) {
                      _self.sysServiceType = responseData.data;
                      for(var i=0;i<responseData.data.length;i++){
                        if(_self.formData.systemUseServices.length>=i+1){
                          _self.formData.systemUseServices[i].fkProductsType = responseData.data[i].systemCode;
                          _self.beginContent.systemUseServices[i].fkProductsType = responseData.data[i].systemCode;
                        }else{
                          _self.formData.systemUseServices.push({
                            "fkResponsibleType":"",
                            "fkProductsType":responseData.data[i].systemCode,
                            "fkSystemId":"",
                            "serviceIsUse":"",
                            "otherName":""
                          });
                        }
                      }
                    },
                    a:function(){
                      bus.$emit('aaa',"2");
                    },
                    //选择无或者0时，是否使用国产品和使用率不用验证
                    changeNumber: function(productsNumber){
                    	if(productsNumber == 0){
                    		bus.$emit('changeNumber',"changeNumber");
                    		//验证过就将验证消息清空
                    		if(this.$refs.systemKeyFkNationalIsProducts){
                    			this.$refs.systemKeyFkNationalIsProducts.clearValidate();
                    		}
                    		if(this.$refs.systemProServices){
                    			this.$refs.systemProServices.clearValidate();
                    		}
                    		//this.$refs.systemKeyProducts.clearValidate();
                    	}else{
                    		bus.$emit('changeNumber',"");
                    	}
                    },
                    
                    //是否使用国产品联动产品率
                    productChange:function(val,index,number){
                    	if(number==1){
                    	  if(val=="1"||val==1){
                    	    this.formData.systemKeyProducts[index].nUseProbability=100;
                    	  }else if(val=="2"||val==2){
                    	    this.formData.systemKeyProducts[index].nUseProbability=0;
                    	  }else if(val=="3"||val==3){
                    	  	this.formData.systemKeyProducts[index].nUseProbability=0;
                    	  }
                    	}else{
                    	  var nUsePr = this.formData.systemKeyProducts[index].nUseProbability;
                    	  if(nUsePr=="100"||nUsePr==100){
                    	    this.formData.systemKeyProducts[index].fkNationalIsProducts = '1';
                    	  }else if(nUsePr=="0"||nUsePr==0){
                    	    this.formData.systemKeyProducts[index].fkNationalIsProducts = '2';
                    	  }else{
                    	    this.formData.systemKeyProducts[index].fkNationalIsProducts ='3';
                    	  }
                    	}
                    	
                    	var nationalIsPro = this.formData.systemKeyProducts[index].fkNationalIsProducts;
                      var probability = this.formData.systemKeyProducts[index].nUseProbability;
                      switch (nationalIsPro) {
                      case "1":
                        if(probability==100){
                          Vue.set(data.nUsePro, index, false);
                        }else{
                          Vue.set(data.nUsePro, index, true);
                        }
                        break;
                      case "2":
                        if(probability==0){
                          Vue.set(data.nUsePro, index, false);
                        }else{
                          Vue.set(data.nUsePro, index, true);
                        }
                        break;
                      case "3":
                        if(100>probability && probability>0){
                          Vue.set(data.nUsePro, index, false);
                        }else{
                          Vue.set(data.nUsePro, index, true);
                        }
                        break;
                      default:
                        break;
                      }
                      if(probability>100||probability<0||parseInt(probability)>100||parseInt(probability)<0){
                      	this.formData.systemKeyProducts[index].nUseProbability = 0;
                      }
                    },
                    getFocus:function(val,index){//获取焦点
                    	if(val==0){
                    		this.formData.systemKeyProducts[index].number = "";
                    	}else{
                    		this.formData.systemKeyProducts[index].number = this.formData.systemKeyProducts[index].number;
                    	}
                    },
                    onBlur:function(val,index){
                    	if(val==""){
                    		this.formData.systemKeyProducts[index].number = 0;
                    	}else{
                    		this.formData.systemKeyProducts[index].number = this.formData.systemKeyProducts[index].number;
                    	}
                    },  
                  getNUseProbabilityFocus:function(val,index){
                    if(val==0){
                    	this.formData.systemKeyProducts[index].nUseProbability = "";
                    }else{
                    	this.formData.systemKeyProducts[index].nUseProbability = this.formData.systemKeyProducts[index].nUseProbability;
                    }	
                  },
                  onNUseProbabilityBlur:function(val,index){
                  	if(val==""){
                  		this.formData.systemKeyProducts[index].nUseProbability = 0;
                  		this.formData.systemKeyProducts[index].fkNationalIsProducts = '';
                  	}else{
                  		this.formData.systemKeyProducts[index].nUseProbability = this.formData.systemKeyProducts[index].nUseProbability;
                  	}
                  },
                },
                created: function() {
                  //获取产品类型
                  this.getProductTypeMethod(this);
                  //获取数量
//                  this.getNumberMethod(this);

                  //获取是否使用国产品
                  this.getNationalProductsMethod(this);
                  //获取服务类型
                  this.getServiceTypeMethod(this);
                  //获取服务责任方类型
                  this.getResponsibleMethod(this);
                },
                mounted: function() {

                }
            })
        })
    })
}())