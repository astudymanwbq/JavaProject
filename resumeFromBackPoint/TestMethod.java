package resumeFromBackPoint;

/**
 * Created by BBQ on 2017/6/22.
 */
public class TestMethod {
    public TestMethod()
    {
        try{
            SiteInfoBean bean = new SiteInfoBean("http://localhost:8080/mytest.txt", "F:\\test","mytest.txt",5);
//SiteInfoBean bean = new SiteInfoBean("http://localhost:8080/down.zip","L:\\temp","weblogic60b2_win.exe",5);
            SiteFileFetch fileFetch = new SiteFileFetch(bean);
            fileFetch.start();
        }
        catch(Exception e){e.printStackTrace ();}
    }
    public static void main(String[] args)
    {
        new TestMethod();
    }
}