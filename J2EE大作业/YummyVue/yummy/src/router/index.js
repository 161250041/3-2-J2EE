import Login from "../components/Login";
import RestaurantLogin from "../components/RestaurantLogin";
import RestaurantList from "../components/RestaurantList";
import ActivePage from "../components/ActivePage";
import RestaurantCommodityList from "../components/RestaurantCommodityList";
import MyHeader from "../components/MyHeader";
import UserOrder from "../components/UserOrder";
import UserInfo from "../components/UserInfo";
import RestaurantHeader from "../components/RestaurantHeader";
import RestaurantInfo from "../components/RestaurantInfo";
import RestaurantManager from "../components/RestaurantManager";
import RestaurantOrder from "../components/RestaurantOrder";
import ManagerLogin from "../components/ManagerLogin";
import ApproveApplication from "../components/ApproveApplication";
import UserStatistics from "../components/UserStatistics";
import RestaurantStatistics from "../components/RestaurantStatistics";
import ManagerStatistics from "../components/ManagerStatistics";

const routers = [
  {
    path: '/',
    name:'login',
    components:{
      default:Login,
      // restaurantLogin:RestaurantLogin
    },
  },
  {
    path:"/activePage/:code/:email",
    name:"activePage",
    component:ActivePage
  },
  {
    path:'/restaurantLogin',
    name:'restaurantLogin',
    components: {
      default:RestaurantLogin
    }
  },
  {
    path:'/managerLogin',
    name:'managerLogin',
    components: {
      default:ManagerLogin
    }
  },
  {
    path: '/buttonTest/:id',
    name:'buttonTest',
    component:ButtonTest
  },
  {
    path: '/restaurantList/:email',
    name:'restaurantList',
    component:RestaurantList
  },
  {
    path: '/restaurantCommodityList/:email/:restaurantId',
    name: 'restaurantCommodityList',
    component:RestaurantCommodityList
  },
  {
    path: '/header',
    name:'header',
    component:MyHeader,
  },
  {
    path:'/userOrder/:email',
    name:'userOrder',
    component:UserOrder
  },
  {
    path: '/userInfo/:email',
    name: 'userInfo',
    component:UserInfo
  },
  {
    path: '/restaurantHeader',
    name:'restaurantHeader',
    component:RestaurantHeader,
  },
  {
    path: '/restaurantInfo/:code',
    name: 'restaurantInfo',
    component:RestaurantInfo
  },
  {
    path: '/restaurantOrder/:code',
    name: 'restaurantOrder',
    component:RestaurantOrder
  },
  {
    path: '/restaurantManager/:code',
    name: 'restaurantManager',
    component:RestaurantManager
  },
  {
    path: '/manager',
    name: 'manager',
    component:Manager
  },
  {
    path: '/approveApplication',
    name:'approveApplication',
    component:ApproveApplication
  },
  {
    path:'/userStatistics/:email',
    name:'userStatistics',
    component:UserStatistics
  },
  {
    path:'/restaurantStatistics/:code',
    name:'restaurantStatistics',
    component:RestaurantStatistics
  },
  {
    path:'/managerStatistics',
    name:'managerStatistics',
    component:ManagerStatistics
  }
];

export default routers;
