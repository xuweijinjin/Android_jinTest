package com.jinwode.jintest.Model;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class UrlModel extends AppCompatActivity implements Serializable
{


    /**
     * code : 200
     * msg : SUCCESS
     * data : {"avatarPath":"","Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyZXNvdXJjZSI6IjEzMzYzOTIzMjY5IiwiZXhwIjoxNjE5NDIwNzM1fQ.mDUvfNf19hrd_VVAtmGcdnUf3IiGrqaZXrOoToU6szY","Expire":86400000,"unitOrProject":"unit","user":{"id":"1056","name":"许总","phone":"13363923269","jobName":"董事长","identityName":"公司总部","departName":"公司领导","perms":["490","460","471","482","472","483","451","473","474","485","496","453","486","454","487","498","455","488","445","456","489","500","457","458"]}}
     */

    private int code;
    private String msg;
    private DataDTO data;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public DataDTO getData()
    {
        return data;
    }

    public void setData(DataDTO data)
    {
        this.data = data;
    }

    public static class DataDTO implements Serializable
    {
        /**
         * avatarPath :
         * Token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyZXNvdXJjZSI6IjEzMzYzOTIzMjY5IiwiZXhwIjoxNjE5NDIwNzM1fQ.mDUvfNf19hrd_VVAtmGcdnUf3IiGrqaZXrOoToU6szY
         * Expire : 86400000
         * unitOrProject : unit
         * user : {"id":"1056","name":"许总","phone":"13363923269","jobName":"董事长","identityName":"公司总部","departName":"公司领导","perms":["490","460","471","482","472","483","451","473","474","485","496","453","486","454","487","498","455","488","445","456","489","500","457","458"]}
         */

        private String avatarPath;
        private String Token;
        private int Expire;
        private String unitOrProject;
        private UserDTO user;

        public String getAvatarPath()
        {
            return avatarPath;
        }

        public void setAvatarPath(String avatarPath)
        {
            this.avatarPath = avatarPath;
        }

        public String getToken()
        {
            return Token;
        }

        public void setToken(String Token)
        {
            this.Token = Token;
        }

        public int getExpire()
        {
            return Expire;
        }

        public void setExpire(int Expire)
        {
            this.Expire = Expire;
        }

        public String getUnitOrProject()
        {
            return unitOrProject;
        }

        public void setUnitOrProject(String unitOrProject)
        {
            this.unitOrProject = unitOrProject;
        }

        public UserDTO getUser()
        {
            return user;
        }

        public void setUser(UserDTO user)
        {
            this.user = user;
        }

        public static class UserDTO implements Serializable
        {
            /**
             * id : 1056
             * name : 许总
             * phone : 13363923269
             * jobName : 董事长
             * identityName : 公司总部
             * departName : 公司领导
             * perms : ["490","460","471","482","472","483","451","473","474","485","496","453","486","454","487","498","455","488","445","456","489","500","457","458"]
             */

            private String id;
            private String name;
            private String phone;
            private String jobName;
            private String identityName;
            private String departName;
            private List<String> perms;

            public String getId()
            {
                return id;
            }

            public void setId(String id)
            {
                this.id = id;
            }

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }

            public String getPhone()
            {
                return phone;
            }

            public void setPhone(String phone)
            {
                this.phone = phone;
            }

            public String getJobName()
            {
                return jobName;
            }

            public void setJobName(String jobName)
            {
                this.jobName = jobName;
            }

            public String getIdentityName()
            {
                return identityName;
            }

            public void setIdentityName(String identityName)
            {
                this.identityName = identityName;
            }

            public String getDepartName()
            {
                return departName;
            }

            public void setDepartName(String departName)
            {
                this.departName = departName;
            }

            public List<String> getPerms()
            {
                return perms;
            }

            public void setPerms(List<String> perms)
            {
                this.perms = perms;
            }
        }
    }
}