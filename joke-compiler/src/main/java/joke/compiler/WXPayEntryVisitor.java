package joke.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.sun.tools.javac.code.ModuleFinder;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class WXPayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private String mPackName;
    private TypeMirror mTypeMirror;
    private Filer mFiler;

    @Override
    public Void visitString(String s, Void unused) {
        mPackName = s;
        return unused;
    }

    @Override
    public Void visitType(TypeMirror t, Void unused) {
        mTypeMirror = t;
        try {
            geneWXPayCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unused;
    }
    public void setFiler(Filer mFiler) {
        this.mFiler = mFiler;
    }

    /**
     * 生成类
     */
    private void geneWXPayCode() throws IOException {
        TypeSpec.Builder classSpecBuilder = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror));
        try {
            JavaFile.builder(mPackName + "wxapi", classSpecBuilder.build())
                    .addFileComment("微信支付自动生成")
                    .build().writeTo(mFiler);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("生成异常");
        }
    }
}
