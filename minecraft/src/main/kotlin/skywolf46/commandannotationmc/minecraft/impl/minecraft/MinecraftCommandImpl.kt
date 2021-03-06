package skywolf46.commandannotationmc.minecraft.impl.minecraft

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import skywolf46.commandannotation.kotlin.data.Arguments
import skywolf46.commandannotationmc.minecraft.CommandAnnotation
import skywolf46.extrautility.data.ArgumentStorage

class MinecraftCommandImpl(starting: String) : Command(starting) {
    override fun execute(p0: CommandSender, p1: String, p2: Array<String>): Boolean {
        val arguments = Arguments(false, "/$p1", ArgumentStorage(), p2, 0)
        arguments.addParameter(p0)
        arguments.addParameter(arguments._storage)
        arguments.addParameter(arguments)
        CommandAnnotation.command.inspect("/$p1", arguments).forEach {
            if (!it.invoke(arguments.increasePointer(true, 0)))
                return false
        }
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): List<String> {
        val arg = Arguments(false, "/$alias", ArgumentStorage(), args, 0)
        arg.addParameter(sender)
        val cplt = CommandAnnotation.command.inspectNextParameter("/$alias", arg)
        return cplt
    }
}