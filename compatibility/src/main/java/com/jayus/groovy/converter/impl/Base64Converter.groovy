
import com.jayus.groovy.converter.IConverter

class Base64Converter implements IConverter {

    @Override
    String encoder(String data) {
        return data.getBytes().encodeBase64().toString()
    }

    @Override
    String decoder(String data) {
        return new String(Base64.decoder.decode(data))
    }
}
